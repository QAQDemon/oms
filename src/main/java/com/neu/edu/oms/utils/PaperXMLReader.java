package com.neu.edu.oms.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/*
 * @Description 试卷模板xml文件的读取器
 **/
public class PaperXMLReader {
    private Element root;
    private Element paper;
    /*
     * @Description 读取xml文件并产生根节点
     * @Param [xmlName文件名]
     * @return
     **/
    public PaperXMLReader(String xmlName)throws DocumentException{
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(xmlName));
        root = document.getRootElement();
        paper=null;
    }

    /*
     * @Description 将xml全部内容以字符串形式返回
     * @Param []
     * @return java.lang.String
     **/
    public String readAllAsString(){
        return root.getDocument().asXML();
    }

    /*
     * @Description 通过ID选择试卷模板
     * @Param [ID试卷模板]从000001开始
     * @return int 1成功 0失败
     **/
    public int setPaperID(String ID){
        List papers = root.elements();
        for (Object p : papers) {
            if(((Element)p).attributeValue("id").equals(ID)){
                paper=((Element)p);
                return 1;
            }
        }
        return 0;
    }

    /*
     * @Description 获得坐标节点的行号和列号
     * @Param [coordinate坐标节点]
     * @return int[row,column] 读到-1说明失败
     **/
    private int[] getCoordinateIndex(Element coordinate){
        int[] indexes={-1,-1};
        try {
            indexes[0]=Integer.parseInt(coordinate.attributeValue("row"));
            indexes[1]=Integer.parseInt(coordinate.attributeValue("column"));
        }catch (NullPointerException e){//无row和column属性异常
            return indexes;
        }
        return indexes;
    }

    /*
     * @Description 获取选定节点的左上和右下两个坐标的行号和列号
     * @Param [node]选定节点
     * @return int[first,last][row,column]读到-1说明失败
     **/
    private  int[][] getFirstAndLastCoordinates(Element node){
        int[][] indexGroup=new int[2][2];
        indexGroup[0][0]=-1;
        List coordinates = node.elements();
        int i=0;
        for (Object coordinate : coordinates) {
            try {
                indexGroup[i][0]=Integer.parseInt(((Element)coordinate).attributeValue("row"));
                indexGroup[i][1]=Integer.parseInt(((Element)coordinate).attributeValue("column"));
                i++;
            }catch (NullPointerException e){//无row和column属性异常
                return indexGroup;
            }
        }
        return indexGroup;
    }

    /*
     * @Description 根据页数ID读取四个同步头的行号和列号
     * @Param [ID页数]从01开始
     * @return int[][] 第一个值-1为无效ID
     **/
    public int[][] getCalibrationGroupByID(String ID){
        int[][] indexGroup=new int[4][2];
        indexGroup[0][0]=-1;
        Element calibrations = paper.element("calibrations");
        List groups = calibrations.elements();
        for (Object group : groups) {
            if (((Element) group).attributeValue("id").equals(ID)) {
                //读取四个同步头的坐标
                List coordinates = ((Element) group).elements();
                int i=0;
                for (Object coordinate : coordinates) {
                    int[] indexes=getCoordinateIndex(((Element)coordinate));
                    indexGroup[i][0] = indexes[0];
                    indexGroup[i][1] = indexes[1];
                    i++;
                }
                return indexGroup;
            }
        }
        return indexGroup;
    }

    /*
     * @Description 获得AB卷标志的两个坐标的行号和列号
     * @Param []
     * @return int[][] 第一个值-1为出错
     **/
    public int[][] getTypeCoordinates(){
        return getFirstAndLastCoordinates(paper.element("type"));
    }

    /*
     * @Description 获得试卷的物理信息，单位为cm
     * @Param [sign 0试卷总体宽度高度，1单位格子的宽度高度]
     * @return double[宽度，高度]
     **/
    public double[] getPhysicalInfo(int sign){
        double[] res=new double[2];
        Element e=paper.element("physicalInfo");
        if (sign == 1) {
            e=e.element("unit");
        }
        res[0]= Double.parseDouble(e.element("width").getText());
        res[1]= Double.parseDouble(e.element("height").getText());
        return res;
    }

    /*
     * @Description 获得试卷信息的两个坐标
     * @Param [sign]0学校 1学年 2学期 3考试名
     * @return int[][] -1异常
     **/
    public int[][] getPaperInfoCoordinates(int sign){
        switch (sign) {
            case 0://学校
                return getFirstAndLastCoordinates(paper.element("university").element("coordinates"));
            case 1://学年
                return getFirstAndLastCoordinates(paper.element("year").element("coordinates"));
            case 2://学期
                return getFirstAndLastCoordinates(paper.element("term").element("coordinates"));
            case 3://考试名
                return getFirstAndLastCoordinates(paper.element("subject").element("coordinates"));
            default://异常输入
                int[][] indexGroup=new int[2][2];
                indexGroup[0][0]=-1;
                return indexGroup;
        }
    }

    /*
     * @Description 获得试卷信息的内容
     * @Param [sign]0学校 1学年 2学期 3考试名
     * @return int[][] ""异常
     **/
    public String getPaperInfoContent(int sign){
        switch (sign) {
            case 0://学校
                return paper.element("university").element("content").getText();
            case 1://学年
                return paper.element("year").element("content").getText();
            case 2://学期
                return paper.element("term").element("content").getText();
            case 3://考试名
                return paper.element("subject").element("content").getText();
            default://异常输入
                return "";
        }
    }

    /*
     * @Description 获得学生信息的两个坐标
     * @Param [sign]0名字 1班级 2考试号 3学号
     * @return int[][] -1异常
     **/
    public int[][] getStudentInfoCoordinates(int sign){
        Element studentInfo=paper.element("studentInfo");
        switch (sign) {
            case 0://名字
                return getFirstAndLastCoordinates(studentInfo.element("name").element("coordinates"));
            case 1://班级
                return getFirstAndLastCoordinates(studentInfo.element("class").element("coordinates"));
            case 2://考试号
                return getFirstAndLastCoordinates(studentInfo.element("examinationRoom").element("coordinates"));
            case 3://学号
                return getFirstAndLastCoordinates(studentInfo.element("studentID").element("coordinates"));
            default://异常输入
                int[][] indexGroup=new int[2][2];
                indexGroup[0][0]=-1;
                return indexGroup;
        }
    }

    /*
     * @Description 获得所有选择题或判断题的坐标
     * @Param [sign 0选择题 1判断题, type 试卷类型AB]
     * @return int[][] -1异常
     **/
    public int[][] getObjectiveQuestionCoordinates(int sign,String type){
        int [][] res = new int[2][2];
        res [0][0]=-1;
        ArrayList<Integer>list = new ArrayList<>();
        ArrayList<Integer>list1 = new ArrayList<>();
        String eName="";
        if (sign == 0) {
            eName="choiceQuestions";
        } else if (sign == 1) {
            eName="judgementQuestions";
        }
        List objectiveQuestions = paper.elements("objectiveQuestions");
        for (Object objectiveQuestion : objectiveQuestions) {
            Element o = (Element) objectiveQuestion;
            if (o.attributeValue("type").equals(type)) {
                Element choiceQuestions=o.element(eName);
                List groups = choiceQuestions.elements();
                for (Object group : groups) {
                    Element g = (Element) group;
                    try {
                        int[][] coordinates = getFirstAndLastCoordinates(g.element("coordinates"));
                        list.add(coordinates[0][0]);
                        list.add(coordinates[1][0]);
                        list1.add(coordinates[0][1]);
                        list1.add(coordinates[1][1]);
                    } catch (NullPointerException e) {//参数缺少异常
                        return res;
                    }
                }
                res=new int [list.size()][2];
                res[0][0]=-1;
                for (int i = 0; i < list.size(); i++) {
                    res[i][0] = list.get(i);
                    res[i][1] = list1.get(i);
                }
                return res;
            }
        }
        return res;
    }

    /*
     * @Description 获得选定组的选择题信息，用json格式返回
     * @Param [objectiveQuestions, ID选择题组号]
     * @return java.lang.String{"type":"s","extend":"d4","choice":"r4","coordinates":{"firstCoordinate":{"row":4,"column":28},"lastCoordinate":{"row":80,"column":45}}}
     **/
    private String getChoiceQuestionByID(Element objectiveQuestions,String ID){
        Element choiceQuestions=objectiveQuestions.element("choiceQuestions");
        List groups = choiceQuestions.elements();
        for (Object group : groups) {
            Element g=(Element)group;
            if(g.attributeValue("id").equals(ID)){
                try {
                    String type = g.element("type").getText();
                    String extend = g.element("extend").getText();
                    String choice = g.element("choice").getText();
                    int [][] coordinates=getFirstAndLastCoordinates(g.element("coordinates"));
                    return "{\"type\":\""+type+"\",\"extend\":\""+extend+"\",\"choice\":\""+choice+"\",\"coordinates\":{\"firstCoordinate\":{\"row\":"+coordinates[0][0]+",\"column\":"+coordinates[0][1]+"},\"lastCoordinate\":{\"row\":"+coordinates[1][0]+",\"column\":"+coordinates[1][1]+"}}}";
                }catch (NullPointerException e){//参数缺少异常
                    return "{}";
                }
            }
        }
        return "{}";
    }

    /*
     * @Description 获得AB卷的选择题信息，用json格式返回
     * @Param [type A或B, ID选择题组号]
     * @return java.lang.String{"type":"s","extend":"d4","choice":"r4","coordinates":{"firstCoordinate":{"row":4,"column":28},"lastCoordinate":{"row":80,"column":45}}}
     **/
    public String getChoiceQuestionByType(String type,String ID) {
        List objectiveQuestions = paper.elements("objectiveQuestions");
        for (Object objectiveQuestion : objectiveQuestions) {
            Element o = (Element) objectiveQuestion;
            if (o.attributeValue("type").equals(type)) {
                return getChoiceQuestionByID(o, ID);
            }
        }
        return "{}";
    }

    /*
     * @Description 获得AB卷所有选择题组信息，用json格式返回
     * @Param [typeA或B]
     * @return java.lang.String{"01":{"type":"s","extend":"d4","choice":"r4","coordinates":{"firstCoordinate":{"row":4,"column":28},"lastCoordinate":{"row":80,"column":45}}}}
     **/
    public String getAllGroupsChoiceQuestion(String type) {
        List objectiveQuestions = paper.elements("objectiveQuestions");
        String result = "{";
        for (Object objectiveQuestion : objectiveQuestions) {
            Element o = (Element) objectiveQuestion;
            if (o.attributeValue("type").equals(type)) {
                Element choiceQuestions = o.element("choiceQuestions");
                List groups = choiceQuestions.elements();
                for (Object group : groups) {
                    Element g = (Element) group;
                    try {
                        String id=g.attributeValue("id");
                        String type1 = g.element("type").getText();
                        String extend = g.element("extend").getText();
                        String choice = g.element("choice").getText();
                        int [][] coordinates=getFirstAndLastCoordinates(g.element("coordinates"));
                        result+= "\""+id+"\":{\"type\":\"" + type1 + "\",\"extend\":\"" + extend + "\",\"choice\":\"" + choice + "\",\"coordinate\":{\"firstCoordinate\":{\"row\":"+coordinates[0][0]+",\"column\":"+coordinates[0][1]+"},\"lastCoordinate\":{\"row\":"+coordinates[1][0]+",\"column\":"+coordinates[1][1]+"}}},";
                    } catch (NullPointerException e) {//参数缺少异常
                        return "{}";
                    }
                }
                if(!result.equals("{"))//去掉逗号
                    result=result.substring(0,result.length() -1);
                result+="}";
                return result;
            }
        }
        return "{}";
    }

    /*
     * @Description 获得选定组的判断题信息，用json格式返回
     * @Param [objectiveQuestions, ID判断题组号]
     * @return java.lang.String{"extend":"d4","choice":"r2","coordinate":{"firstCoordinate":{"row":4,"column":28},"lastCoordinate":{"row":80,"column":45}}}}
     **/
    private String getJudgementQuestionByID(Element objectiveQuestions,String ID){
        Element judgementQuestions=objectiveQuestions.element("judgementQuestions");
        List groups = judgementQuestions.elements();
        for (Object group : groups) {
            Element g=(Element)group;
            if(g.attributeValue("id").equals(ID)){
                try {
                    String extend = g.element("extend").getText();
                    String choice = g.element("choice").getText();
                    int [][] coordinates=getFirstAndLastCoordinates(g.element("coordinates"));
                    return "{\"extend\":\""+extend+"\",\"choice\":\""+choice+"\",\"coordinate\":{\"firstCoordinate\":{\"row\":"+coordinates[0][0]+",\"column\":"+coordinates[0][1]+"},\"lastCoordinate\":{\"row\":"+coordinates[1][0]+",\"column\":"+coordinates[1][1]+"}}}";
                }catch (NullPointerException e){//参数缺少异常
                    return "{}";
                }
            }
        }
        return "{}";
    }

    /*
     * @Description 获得AB卷的判断题信息，用json格式返回
     * @Param [type A或B, ID判断题组号]
     * @return java.lang.String{{"extend":"d4","choice":"r2","coordinate":{"firstCoordinate":{"row":4,"column":28},"lastCoordinate":{"row":80,"column":45}}}}
     **/
    public String getJudgementQuestionByType(String type,String ID) {
        List objectiveQuestions = paper.elements("objectiveQuestions");
        for (Object objectiveQuestion : objectiveQuestions) {
            Element o = (Element) objectiveQuestion;
            if (o.attributeValue("type").equals(type)) {
                return getJudgementQuestionByID(o, ID);
            }
        }
        return "{}";
    }

    /*
     * @Description 获得AB卷所有判断题组信息，用json格式返回
     * @Param [typeA或B]
     * @return java.lang.String{"01":{"extend":"d4","choice":"r2","coordinate":{"firstCoordinate":{"row":4,"column":28},"lastCoordinate":{"row":80,"column":45}}}}
     **/
    public String getAllGroupsJudgementQuestion(String type) {
        List objectiveQuestions = paper.elements("objectiveQuestions");
        String result = "{";
        for (Object objectiveQuestion : objectiveQuestions) {
            Element o = (Element) objectiveQuestion;
            if (o.attributeValue("type").equals(type)) {
                Element judgementQuestions = o.element("judgementQuestions");
                List groups = judgementQuestions.elements();
                for (Object group : groups) {
                    Element g = (Element) group;
                    try {
                        String id=g.attributeValue("id");
                        String extend = g.element("extend").getText();
                        String choice = g.element("choice").getText();
                        int [][] coordinates=getFirstAndLastCoordinates(g.element("coordinates"));
                        result+= "\""+id+"\":{" + "\"extend\":\"" + extend + "\",\"choice\":\"" + choice + "\",\"coordinate\":{\"firstCoordinate\":{\"row\":"+coordinates[0][0]+",\"column\":"+coordinates[0][1]+"},\"lastCoordinate\":{\"row\":"+coordinates[1][0]+",\"column\":"+coordinates[1][1]+"}}},";
                    } catch (NullPointerException e) {//参数缺少异常
                        return "{}";
                    }
                }
                if(!result.equals("{"))//去掉逗号
                    result=result.substring(0,result.length() -1);
                result+="}";
                return result;
            }
        }
        return "{}";
    }

    /*
     * @Description 获得选定组的客观题信息，用json格式返回
     * @Param [ID客观题组号]
     * @return java.lang.String{"page":1,"coordinates":{"firstCoordinate":{"row":4,"column":28},"lastCoordinate":{"row":80,"column":45}}}
     **/
    public String getSubjectiveQuestionByID(String ID){
        Element subjectiveQuestions=paper.element("subjectiveQuestions");
        List groups = subjectiveQuestions.elements();
        for (Object group : groups) {
            Element g=(Element)group;
            if(g.attributeValue("id").equals(ID)){
                try {
                    String page = g.element("page").getText();
                    int [][] coordinates=getFirstAndLastCoordinates(g.element("coordinates"));
                    return "{\"page\":"+page+",\"coordinates\":{\"firstCoordinate\":{\"row\":"+coordinates[0][0]+",\"column\":"+coordinates[0][1]+"},\"lastCoordinate\":{\"row\":"+coordinates[1][0]+",\"column\":"+coordinates[1][1]+"}}}";
                }catch (NullPointerException e){//参数缺少异常
                    return "{}";
                }
            }
        }
        return "{}";
    }

    /*
     * @Description 根据页数获得客观题的坐标
     * @Param [page]
     * @return int[][]
     **/
    public int[][] getSubjectiveQuestionCoordinates(String page){
        ArrayList<Integer>list = new ArrayList<>();
        ArrayList<Integer>list1 = new ArrayList<>();
        Element subjectiveQuestions=paper.element("subjectiveQuestions");
        List groups = subjectiveQuestions.elements();
        for (Object group : groups) {
            Element g=(Element)group;
            if(g.element("page").getText().equals(page)){
                int [][] coordinates=getFirstAndLastCoordinates(g.element("coordinates"));
                list.add(coordinates[0][0]);
                list.add(coordinates[1][0]);
                list1.add(coordinates[0][1]);
                list1.add(coordinates[1][1]);
            }
        }
        int [][] res=new int [list.size()][2];
        res[0][0]=-1;
        for (int i = 0; i < list.size(); i++) {
            res[i][0] = list.get(i);
            res[i][1] = list1.get(i);
        }
        return res;
    }

    /*
     * @Description 获得所有客观题组信息，用json格式返回
     * @Param []
     * @return java.lang.String{"01":{"page":1,"coordinates":{"firstCoordinate":{"row":4,"column":10},"lastCoordinate":{"row":80,"column":27}}}}
     **/
    public String getAllGroupsSubjectiveQuestion() {
        Element subjectiveQuestions = paper.element("subjectiveQuestions");
        List groups = subjectiveQuestions.elements("group");
        String result = "{";
        for (Object group : groups) {
            Element g = (Element) group;
            try {
                String id=g.attributeValue("id");
                String page = g.element("page").getText();
                int [][] coordinates=getFirstAndLastCoordinates(g.element("coordinates"));
                result+= "\""+id+"\":{" + "\"page\":" + page + ",\"coordinates\":{\"firstCoordinate\":{\"row\":"+coordinates[0][0]+",\"column\":"+coordinates[0][1]+"},\"lastCoordinate\":{\"row\":"+coordinates[1][0]+",\"column\":"+coordinates[1][1]+"}}},";
            } catch (NullPointerException e) {//参数缺少异常
                return "{}";
            }
        }
        if(!result.equals("{"))//去掉逗号
            result=result.substring(0,result.length() -1);
        result+="}";
        return result;
    }
}
