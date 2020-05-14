package com.neu.edu.oms.service.impl;

import com.neu.edu.oms.dao.GoalMapper;
import com.neu.edu.oms.dao.PointMapper;
import com.neu.edu.oms.entity.*;
import com.neu.edu.oms.service.DataAnalysisService;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class DataAnalysisServiceImpl implements DataAnalysisService {

    @Resource
    private GoalMapper goalMapper;

    @Resource
    private PointMapper pointMapper;


    public Map<String, Object > getSegmentData(List<PaperScanFull> papers){
        if(papers == null){
            return null;
        }
        Integer scoreall = papers.get(0).getScore();
        Integer low = (int)( scoreall*0.4);
        Integer pass = (int)(scoreall*0.6);
        Integer medium = (int)(scoreall*0.7);
        Integer good = (int)(scoreall*0.8);
        Integer excellent = (int)(scoreall*0.9);
        Integer lownum = 0;
        Integer unpassnum = 0;
        Integer passnum = 0;
        Integer mediumnum = 0;
        Integer goodnum = 0;
        Integer excellentnum = 0;
        for(PaperScanFull paper:papers){
            if(paper.getPaperScanId() == 0){
                continue;
            }else if(paper.getScoreGet() < low){
                lownum++;
            }else if(paper.getScoreGet() < pass){
                unpassnum++;
            }else if(paper.getScoreGet() < medium){
                passnum++;
            }else if(paper.getScoreGet() < good){
                mediumnum++;
            }else if(paper.getScoreGet() < excellent){
                goodnum++;
            }else {
                excellentnum++;
            }
        }
        String level1 = "0~"+(low-1);
        String level2 = low+"~"+(pass-1);
        String level3 = pass+"~"+(medium-1);
        String level4 = medium+"~"+(good-1);
        String level5 = good+"~"+(excellent-1);
        String level6 = excellent+"~"+scoreall;
        List<String> level = new ArrayList<String>();
        List<Integer> count = new ArrayList<Integer>();
        level.add(level1);
        level.add(level2);
        level.add(level3);
        level.add(level4);
        level.add(level5);
        level.add(level6);
        count.add(lownum);
        count.add(unpassnum);
        count.add(passnum);
        count.add(mediumnum);
        count.add(goodnum);
        count.add(excellentnum);
        List<Piedata> piedatas = new ArrayList<Piedata>();
        for(int i=0; i<6; i++){
            piedatas.add(new Piedata(count.get(i), level.get(i)));
        }
        Map<String, Object > segmentdatamap = new HashMap<String, Object>();
        segmentdatamap.put("level", level);
        segmentdatamap.put("count", count);
        segmentdatamap.put("piedatas", piedatas);
        return segmentdatamap;
    }

    public List<Float> getratebardata(List<PaperScanFull> papers){
        if(papers == null){
            return null;
        }
        Integer scoreall = papers.get(0).getScore();
        Integer pass = (int)(scoreall*0.6);
        Integer good = (int)(scoreall*0.8);
        Integer excellent = (int)(scoreall*0.9);
        Integer allnum=0;
        Integer unpassnum = 0;
        Integer passnum = 0;
        Integer goodnum = 0;
        Integer excellentnum = 0;
        for(PaperScanFull paper:papers){
            if(paper.getPaperScanId() == 0){
                continue;
            }else if(paper.getScoreGet() < pass){
                unpassnum++;
                allnum++;
            }else if(paper.getScoreGet() < good){
                passnum++;
                allnum++;
            }else if(paper.getScoreGet() < excellent){
                goodnum++;
                allnum++;
            }else {
                excellentnum++;
                allnum++;
            }
        }
        passnum = passnum+goodnum+excellentnum;
        goodnum = goodnum+excellentnum;
        float unpassrate = ((float)unpassnum/allnum)*100;
        float passrate = ((float)passnum/allnum)*100;
        float goodrate = ((float)goodnum/allnum)*100;
        float excellentrate = ((float)excellentnum/allnum)*100;
        List<Float> ratebardata = new ArrayList<Float>(4);
        ratebardata.add(unpassrate);
        ratebardata.add(passrate);
        ratebardata.add(goodrate);
        ratebardata.add(excellentrate);
        return ratebardata;
    }

    public Map<String, Object> getgoalradardata(List<PaperScanFull> papers){
        List<Radardata> radardatas = new ArrayList<Radardata>(10);
        List<Integer> dataList = new ArrayList<Integer>(10);
        Map<Integer, Score> scoreMap = new HashMap<Integer, Score>();
        for(PaperScanFull paperScanFull:papers){
            if(paperScanFull.getPaperScanId() == 0){
                continue;
            }
            List<ObjMark> objMarks = paperScanFull.getObjMarks();
            for(ObjMark objMark:objMarks){
                if(scoreMap.get(objMark.getGoalId()) == null){
                    Score score = new Score(objMark.getGoalId());
                    score.setScoreget(score.getScoreget()+objMark.getScoreGet());
                    score.setScoreall(score.getScoreall()+objMark.getScore());
                    scoreMap.put(objMark.getGoalId(), score);
                }else {
                    Score score = scoreMap.get(objMark.getGoalId());
                    score.setScoreget(score.getScoreget()+objMark.getScoreGet());
                    score.setScoreall(score.getScoreall()+objMark.getScore());
                }
            }
            List<SubjScore> subjScores = paperScanFull.getSubjScores();
            for(SubjScore subjScore:subjScores){
                if(scoreMap.get(subjScore.getGoalId()) == null){
                    Score score = new Score(subjScore.getGoalId());
                    score.setScoreget(score.getScoreget()+subjScore.getScoreGet());
                    score.setScoreall(score.getScoreall()+subjScore.getScore());
                    scoreMap.put(subjScore.getGoalId(), score);
                }else{
                    Score score = scoreMap.get(subjScore.getGoalId());
                    score.setScoreget(score.getScoreget()+subjScore.getScoreGet());
                    score.setScoreall(score.getScoreall()+subjScore.getScore());
                }
            }
        }
        List<Score> scores = new ArrayList<Score>(10);
        for(Integer key:scoreMap.keySet()){
            scores.add(scoreMap.get(key));
        }
        scores.sort(new Comparator<Score>() {
            @Override
            public int compare(Score o1, Score o2) {
                return o1.getId()-o2.getId();
            }
        });
        for(Score score:scores){
            Goal goal = goalMapper.selectByPrimaryKey(score.getId());
            radardatas.add(new Radardata(goal.getGoalName()));
            float scorerate = (float)(score.getScoreget())/score.getScoreall();
            Integer scoreget = (int)(scorerate*100);
            dataList.add(scoreget);
        }
        Map<String, Object> goalradardata = new HashMap<String, Object>();
        goalradardata.put("radardatas", radardatas);
        goalradardata.put("dataList", dataList);
        return goalradardata;
    }

    public Map<String, Object> getpointradardata(List<PaperScanFull> papers){
        List<Radardata> radardatas = new ArrayList<Radardata>(10);
        List<Integer> dataList = new ArrayList<Integer>(10);
        Map<Integer, Score> scoreMap = new HashMap<Integer, Score>();
        for(PaperScanFull paperScanFull:papers){
            if(paperScanFull.getPaperScanId() == 0){
                continue;
            }
            List<ObjMark> objMarks = paperScanFull.getObjMarks();
            for(ObjMark objMark:objMarks){
                if(scoreMap.get(objMark.getPointId()) == null){
                    Score score = new Score(objMark.getPointId());
                    score.setScoreget(score.getScoreget()+objMark.getScoreGet());
                    score.setScoreall(score.getScoreall()+objMark.getScore());
                    scoreMap.put(objMark.getPointId(), score);
                }else {
                    Score score = scoreMap.get(objMark.getPointId());
                    score.setScoreget(score.getScoreget()+objMark.getScoreGet());
                    score.setScoreall(score.getScoreall()+objMark.getScore());
                }
            }
            List<SubjScore> subjScores = paperScanFull.getSubjScores();
            for(SubjScore subjScore:subjScores){
                if(scoreMap.get(subjScore.getPointId()) == null){
                    Score score = new Score(subjScore.getPointId());
                    score.setScoreget(score.getScoreget()+subjScore.getScoreGet());
                    score.setScoreall(score.getScoreall()+subjScore.getScore());
                    scoreMap.put(subjScore.getPointId(), score);
                }else{
                    Score score = scoreMap.get(subjScore.getPointId());
                    score.setScoreget(score.getScoreget()+subjScore.getScoreGet());
                    score.setScoreall(score.getScoreall()+subjScore.getScore());
                }
            }
        }
        List<Score> scores = new ArrayList<Score>(10);
        for(Integer key:scoreMap.keySet()){
            scores.add(scoreMap.get(key));
        }
        scores.sort(new Comparator<Score>() {
            @Override
            public int compare(Score o1, Score o2) {
                return o1.getId()-o2.getId();
            }
        });
        for(Score score:scores){
            Point point = pointMapper.selectByPrimaryKey(score.getId());
            radardatas.add(new Radardata(point.getPointName()));
            float scorerate = (float)(score.getScoreget())/score.getScoreall();
            Integer scoreget = (int)(scorerate*100);
            dataList.add(scoreget);
        }
        Map<String, Object> pointradardata = new HashMap<String, Object>();
        pointradardata.put("radardatas", radardatas);
        pointradardata.put("dataList", dataList);
        return pointradardata;
    }

}
