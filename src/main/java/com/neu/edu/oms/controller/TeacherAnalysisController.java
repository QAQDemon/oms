package com.neu.edu.oms.controller;

import com.google.gson.Gson;
import com.neu.edu.oms.entity.Class;
import com.neu.edu.oms.entity.PaperScanFull;
import com.neu.edu.oms.service.DataAnalysisService;
import com.neu.edu.oms.service.DataInsertService;
import com.neu.edu.oms.service.TeacherAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.channels.ClosedSelectorException;
import java.util.*;

@Controller
@RequestMapping("/Teacher")
public class TeacherAnalysisController {

    @Autowired
    private DataInsertService dataInsertService;

    @Autowired
    private TeacherAnalysisService teacherAnalysisService;

    @Autowired
    private DataAnalysisService dataAnalysisService;

    private static List<PaperScanFull> paperscans = null;

    private static List<PaperScanFull> paperscansall = null;

    @RequestMapping("/insertstudent")
    public String insertstudent(){
        dataInsertService.StudentInsert(26);
        return "Teacher";
    }

    @RequestMapping("/insertpaperscan")
    public String insertinsertpaperscan(){
        dataInsertService.PaperScanInsert();
        return "Teacher";
    }

    @RequestMapping("/insertgoalandpoint")
    public String insertgoalandpoint(){
        dataInsertService.GoalAndPointInsert();
        return "Teacher";
    }

    @RequestMapping("/insertobj")
    public String insertobj(){
        dataInsertService.ObjMarkInsert();
        return "Teacher";
    }

    @RequestMapping("/insertsubj")
    public String insertsubj(){
        dataInsertService.SubjMarkInsert();
        dataInsertService.SubjMarkInsert();
        return "Teacher";
    }

    @RequestMapping("/test")
    public String test(Model model){
        List<Class> classList =  teacherAnalysisService.getClassAndAnswerByTeacherId(1);
        HashSet<Integer> teacheryearsset = new HashSet();
        List<Integer> teacheryearslist = new ArrayList<Integer>();
        for(Class class1:classList){
            teacheryearsset.add(class1.getTeachYear());
        }
        teacheryearslist.addAll(teacheryearsset);
        model.addAttribute("classes", classList);
        model.addAttribute("teachyears", teacheryearslist);
        return "Teacher";
    }

    @RequestMapping("/getscore")
    @ResponseBody
    public String getscoretest1(Integer classId, Integer answerId){
        List<PaperScanFull> paperScanFullList = teacherAnalysisService.getAllpapers(classId, answerId);
        paperscans = paperScanFullList;
        Gson gson = new Gson();
        String paperScanListjson = gson.toJson(paperScanFullList);
//        System.out.println(paperScanListjson);

        List<PaperScanFull> paperScanFullListall = teacherAnalysisService.getpapersByanswerId(answerId);
        paperscansall = paperScanFullListall;
//        System.out.println(gson.toJson(paperScanFullListall));
//        System.out.println(paperScanFullList.size());
        return paperScanListjson;
    }

    //第一个图的数据
    @RequestMapping("/getsegmentdata")
    @ResponseBody
    public String getsegmentdata(Integer classId, Integer answerId){

        String segmentdatajson;
        if(paperscans == null){
            paperscans = teacherAnalysisService.getAllpapers(classId, answerId);
            Map<String, Object> segmentdatamap = dataAnalysisService.getSegmentData(paperscans);
            Gson gson = new Gson();
            segmentdatajson = gson.toJson(segmentdatamap);
        }else{
            Map<String, Object> segmentdatamap = dataAnalysisService.getSegmentData(paperscans);
            Gson gson = new Gson();
            segmentdatajson = gson.toJson(segmentdatamap);
        }
//        System.out.println(segmentdatajson);
        return segmentdatajson;
    }

    //第二个图的数据
    @RequestMapping("/getsegmentdataall")
    @ResponseBody
    public String getsegmentdataall(Integer classId, Integer answerId){

        String segmentdatajson;
        if(paperscansall == null){
            paperscansall = teacherAnalysisService.getpapersByanswerId(answerId);
            Map<String, Object> segmentdatamap = dataAnalysisService.getSegmentData(paperscansall);
            Gson gson = new Gson();
            segmentdatajson = gson.toJson(segmentdatamap);
        }else{
            Map<String, Object> segmentdatamap = dataAnalysisService.getSegmentData(paperscansall);
            Gson gson = new Gson();
            segmentdatajson = gson.toJson(segmentdatamap);
        }
//        System.out.println(segmentdatajson);
        return segmentdatajson;
    }

    //第三个图的数据
    @RequestMapping("/getratedata")
    @ResponseBody
    public String getratedata(Integer classId, Integer answerId){

        String ratedatajson;
        List<Float> ratedataclass;
        List<Float> ratedataall;
        if(paperscans == null){
            paperscans = teacherAnalysisService.getAllpapers(classId, answerId);
            ratedataclass = dataAnalysisService.getratebardata(paperscans);
        }else{
            ratedataclass = dataAnalysisService.getratebardata(paperscans);
        }
        if(paperscansall == null){
            paperscansall = teacherAnalysisService.getpapersByanswerId(answerId);
            ratedataall = dataAnalysisService.getratebardata(paperscansall);
        }else{
            ratedataall = dataAnalysisService.getratebardata(paperscansall);
        }
        Map<String, Object> ratemap = new HashMap<String, Object>();
        ratemap.put("rateclass", ratedataclass);
        ratemap.put("rateall", ratedataall);
        Gson gson = new Gson();
        ratedatajson = gson.toJson(ratemap);
        System.out.println(ratedatajson);
        return ratedatajson;
    }

    //第四个图数据
    @RequestMapping("/getgoalradardata")
    @ResponseBody
    public String getgoalradardata(Integer classId, Integer answerId){
        Map<String, Object> ratemap = new HashMap<String, Object>();
        Map<String, Object> goalratedata1 = new HashMap<String, Object>();
        Map<String, Object> goalratedata2 = new HashMap<String, Object>();
        String goalradardatajson;
        if(paperscans == null){
            paperscans = teacherAnalysisService.getAllpapers(classId, answerId);
            goalratedata1 = dataAnalysisService.getgoalradardata(paperscans);
        }else{
            goalratedata1 = dataAnalysisService.getgoalradardata(paperscans);
        }
        if(paperscansall == null){
            paperscansall = teacherAnalysisService.getpapersByanswerId(answerId);
            goalratedata2 = dataAnalysisService.getgoalradardata(paperscansall);
        }else{
            goalratedata2 = dataAnalysisService.getgoalradardata(paperscansall);
        }
        ratemap.put("radardatas", goalratedata1.get("radardatas"));
        ratemap.put("dataList1", goalratedata1.get("dataList"));
        ratemap.put("dataList2", goalratedata2.get("dataList"));
        Gson gson = new Gson();
        goalradardatajson = gson.toJson(ratemap);
        System.out.println(goalradardatajson);
        return goalradardatajson;
    }

    //第五个图数据
    @RequestMapping("/getpointradardata")
    @ResponseBody
    public String getpointradardata(Integer classId, Integer answerId){
        Map<String, Object> ratemap = new HashMap<String, Object>();
        Map<String, Object> goalratedata1 = new HashMap<String, Object>();
        Map<String, Object> goalratedata2 = new HashMap<String, Object>();
        String pointradardatajson;
        if(paperscans == null){
            paperscans = teacherAnalysisService.getAllpapers(classId, answerId);
            goalratedata1 = dataAnalysisService.getgoalradardata(paperscans);
        }else{
            goalratedata1 = dataAnalysisService.getgoalradardata(paperscans);
        }
        if(paperscansall == null){
            paperscansall = teacherAnalysisService.getpapersByanswerId(answerId);
            goalratedata2 = dataAnalysisService.getgoalradardata(paperscansall);
        }else{
            goalratedata2 = dataAnalysisService.getgoalradardata(paperscansall);
        }
        ratemap.put("radardatas", goalratedata1.get("radardatas"));
        ratemap.put("dataList1", goalratedata1.get("dataList"));
        ratemap.put("dataList2", goalratedata2.get("dataList"));
        Gson gson = new Gson();
        pointradardatajson = gson.toJson(ratemap);
        System.out.println(pointradardatajson);
        return pointradardatajson;
    }





    @ResponseBody
    @RequestMapping("/ceshi")
    public String requestbody(){
        int a=0,b=0;
        for(int i=0; i<200; i++){
            Byte flag = (byte)(Math.random()+0.65);
            if(flag == 1){
                a++;
            }else{
                b++;
            }
            System.out.println(flag);
        }
        System.out.println(a+"------------"+b);
        return "Teacher";
    }
}
