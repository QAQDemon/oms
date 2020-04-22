package com.neu.edu.oms.utils.imageUtils;

import com.neu.edu.oms.utils.PaperXMLReader;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.dom4j.DocumentException;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;

import java.util.ArrayList;
import java.util.List;

/*
 * @Description
 * @author demon
 * @version v1.0
 **/
public class SegmentUtils {
    private int[] startIndexs;
    private Mat src;
    private int page;//1 2
    private String type;//A B
    private PaperXMLReader paperXMLReader;
    //像素坐标
    private double xLength;
    private double yLength;
    private double x0;
    private double y0;

    private static final double RATE_LIMIT=0.3;

    /*
     * @Description 初始化，将试卷图片调整为正，上短下长
     * @Param [mat图片, s扫描的4个同步头坐标,xmlName,ID试卷]
     * @return
     **/
    public SegmentUtils(Mat mat,int[] s,String xmlName,String ID)throws DocumentException {
        startIndexs=s;
        src=mat;
        paperXMLReader = new PaperXMLReader(xmlName);
        paperXMLReader.setPaperID(ID);
        judgePage();
    }

    /*
     * @Description 判断试卷是第几页，根据xml文件同步头的比率
     * @Param []
     * @return void
     **/
    private void judgePage(){
        int[][] calibration1 = paperXMLReader.getCalibrationGroupByID("01");
        int[][] calibration2 = paperXMLReader.getCalibrationGroupByID("02");
        double[] k=new double[2];
        k[0] = (double)Math.abs(calibration1[1][1] - calibration1[0][1]) / (double)Math.abs(calibration1[3][1] - calibration1[2][1]);
        k[1] = (double)Math.abs(calibration2[1][1] - calibration2[0][1]) / (double)Math.abs(calibration2[3][1] - calibration2[2][1]);
        double temp=(double)Math.abs(startIndexs[1]-startIndexs[0])/(double)Math.abs(startIndexs[4]-startIndexs[3]);
        if(Math.abs(temp-k[0])<Math.abs(temp-k[1])){//比率接近第一页
            page=1;
        }else page=2;
    }

    /*
     * @Description 根据xml读出的row，col计算在图片上的像素坐标
     * @Param [row, col]
     * @return double[x,y]
     **/
    private double[] calcuteIndex(int row,int col){
        double[] res=new double[2];//x y
        if (xLength == 0.0) {
            String p = "01";
            if (page == 2)
                p = "02";
            int[][] calibration = paperXMLReader.getCalibrationGroupByID(p);
            xLength=(double)Math.abs(startIndexs[1] - startIndexs[0])/(double)Math.abs(calibration[1][1] - calibration[0][1]);
            yLength=(double)Math.abs(startIndexs[5] - startIndexs[2])/(double)Math.abs(calibration[0][0] - calibration[2][0]);
            x0=startIndexs[0]-xLength*(calibration[0][1]-1);
            y0=startIndexs[2]-yLength*(calibration[0][0]-1);
        }
        res[0]=x0+(col-1)*xLength;
        res[1]=y0+(row-1)*yLength;
        return res;
    }

    /*
     * @Description 根据坐标分割图片并保存
     * @Param [name图片名字, coordinates [[row,col],[row,col]]
     * @return Mat
     **/
    public Mat splitImg(String name,int[][] coordinates){
        double[] index=calcuteIndex(coordinates[0][0],coordinates[0][1]);
        double[] index1=calcuteIndex(coordinates[1][0],coordinates[1][1]);
        Rect rect1 = new Rect(new Point(index[0],index[1]),new Point(index1[0],index1[1]));
        Mat mat=new Mat(src,rect1);
        Imgcodecs.imwrite("D:/jpg/"+name+".jpg", mat);
        return mat;
    }

    /*
     * @Description 分割类型的图片 A或B，并识别
     * @Param []
     * @return void
     **/
    public void slipAndJudgeTypeImg(){
        int[][] coordinates=paperXMLReader.getTypeCoordinates();
        if(coordinates[0][0]==-1)//读取失败
            return;
        Mat t=splitImg("type", coordinates);
        //识别类型
        int res=new ImgRecognition().judgeAB(t);
        if(res==65)
            type = "A";
        else type="B";
    }

    /*
     * @Description 分割试卷信息的图片
     * @Param [sign]0学校 1学年 2学期 3考试名
     * @return void
     **/
    public void slipPaperInfoImg(int sign){
        int[][] coordinates=paperXMLReader.getPaperInfoCoordinates(sign);
        if(coordinates[0][0]==-1)//读取失败
            return;
        switch (sign) {
            case 0://学校
                splitImg("university", coordinates);
            case 1://学年
                splitImg("year", coordinates);
            case 2://学期
                splitImg("term", coordinates);
            case 3://考试名
                splitImg("subject", coordinates);
        }
    }

    /*
     * @Description 分割学生信息的图片
     * @Param [sign]0名字 1班级 2考试号 3学号
     * @return void
     **/
    public void slipStudentInfoImg(int sign){
        int[][] coordinates=paperXMLReader.getStudentInfoCoordinates(sign);
        if(coordinates[0][0]==-1)//读取失败
            return;
        switch (sign) {
            case 0://名字
                splitImg("name", coordinates);
            case 1://班级
                splitImg("class", coordinates);
            case 2://考试号
                splitImg("examinationRoom", coordinates);
            case 3://学号
                splitImg("studentID", coordinates);
        }
    }

    /*
     * @Description 分割题目的图片并返回选项结果
     * @Param [sign 0主观题 1选择题 2判断题]
     * @return String[题目数]
     **/
    public String[] slipAndScanQuestionImg(int sign){
        int[][] coordinates=new int[2][2];
        String name="";
        if (sign == 0) {
            coordinates=paperXMLReader.getSubjectiveQuestionCoordinates(String.valueOf(page));
            name="SubjectiveQuestion";
        } else if (sign == 1) {
            coordinates=paperXMLReader.getObjectiveQuestionCoordinates(0,type);
            name="ChoiceQuestion";
        } else if (sign == 2) {
            coordinates=paperXMLReader.getObjectiveQuestionCoordinates(1,type);
            name="JudgementQuestion";
        }
        if(coordinates[0][0]==-1)//读取失败
            return null;
        List<String[]> resList = new ArrayList<>();
        for (int i = 0; i < coordinates.length ; i+=2) {
            //分割
            int[][] temp = new int[2][2];
            temp[0][0]=coordinates[i][0];
            temp[0][1]=coordinates[i][1];
            temp[1][0]=coordinates[i+1][0];
            temp[1][1]=coordinates[i+1][1];
            splitImg(name+(i/2+1), temp);
            //读取xml
            String json="";
            String id="";
            if((i/2+1)<9)
                id="0"+(i/2+1);
            else id+=(i/2+1);
            if (sign == 1)
                json = paperXMLReader.getChoiceQuestionByType(type, id);
            else if (sign == 2)
                json = paperXMLReader.getJudgementQuestionByType(type, id);
            if (!json.equals("")) {
                JsonParser parser = new JsonParser();
                JsonElement element = parser.parse(json);
                if (element.isJsonObject()) {
                    JsonObject object = element.getAsJsonObject();
                    String extend=object.get("extend").getAsString();
                    String choice=object.get("choice").getAsString();
                    //扫描选项
                    resList.add(choiceScan("D:/jpg/" + name + (i / 2 + 1) + ".jpg", extend, choice, RATE_LIMIT));
                }
            }
        }
        //转换成String[]
        int count=0;
        for (String[] strings : resList) {
            count+=strings.length;
        }
        String[] res = new String[count];
        count=0;
        for (String[] strings : resList) {
            for (String s : strings) {
                res[count++]=s;
            }
        }
        return res;
    }

    /*
     * @Description 扫描获得选择题涂点结果
     * @Param [imgName图片名字, extend扩展d5, choice选项r4, rateLimit黑点率限制]
     * @return String[] 1涂 0未涂 ["1000","0100"]
     **/
    public String[] choiceScan(String imgName,String extend,String choice,double rateLimit){
        MatUtils matUtils = new MatUtils(imgName);
        matUtils.getMinBorder(0);//消黑边
        int width = matUtils.getWidth();
        int height = matUtils.getHeight();
        int extendNum = Integer.parseInt(extend.substring(1,2));//扩展数
        String extendSign=extend.substring(0,1);//r右 d下
        int choiceNum = Integer.parseInt(choice.substring(1,2));//选项数
        String[] res=new String[extendNum+1];
        double aHeight=0, aWidth=0;
        //扫描黑点率
        if(extendSign.equals("d")||extendSign.equals("D")){//向下分布
            aHeight=(double)height/(extendNum+1);
            aWidth=(double)width/choiceNum;
        }else if(extendSign.equals("r")||extendSign.equals("R")){//向右分布
            aHeight=(double)height/choiceNum;
            aWidth=(double)width/(extendNum+1);
        }
        for (int i = 0; i < extendNum+1; i++) {
            res[i]="";
            for (int j = 0; j < choiceNum; j++) {
                double rate;
                if(extendSign.equals("d")||extendSign.equals("D"))
                    rate=matUtils.getAreaBlackRate(new Point(aWidth*j,aHeight*i),new Point(aWidth*(j+1),aHeight*(i+1)));
                else rate=matUtils.getAreaBlackRate(new Point(aWidth*i,aHeight*j),new Point(aWidth*(i+1),aHeight*(j+1)));
                if(rate>rateLimit)
                    res[i]+="1";
                else res[i] += "0";
            }
        }
        return res;
    }

    public static void main(String[] args) {
        // 这个必须要写,不写报java.lang.UnsatisfiedLinkError
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);


        MatUtils matUtils = new MatUtils("C:\\Users\\demon\\Desktop\\work2\\ajt1.jpg");
        if (matUtils.isMatEmpty()) {
            return;
        }
        Mat src=matUtils.preprocess();
        Imgcodecs.imwrite("D:/jpg/1.jpg", src);
        RectifyUtils rectifyUtils=new RectifyUtils(src);
        Mat mat=rectifyUtils.rotationMat();
        Imgcodecs.imwrite("D:/jpg/2.jpg", mat);
        int[] index=rectifyUtils.getSynchronizationHead(100, 40);
        Mat rot = rectifyUtils.getSrc();
        SegmentUtils segmentUtils = null;
        try {
            segmentUtils = new SegmentUtils(rot,index,"src/main/resources/paperTemplate.xml","000001");
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        segmentUtils.slipAndJudgeTypeImg();
        segmentUtils.slipAndScanQuestionImg(1);
        segmentUtils.slipAndScanQuestionImg(2);



    }
}
