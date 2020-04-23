package com.neu.edu.oms.service;

/*
 * @Author zongyinxiao
 * @Date 2020/4/5 0:28
 * @Description 写一个java类插入一点假数据.
 * @Since version-1.0
 **/
public interface DataInsertService {
//    插入学生数据
    int StudentInsert(int num);
    //插入扫描试卷数据
    int PaperScanInsert();
    //插入goal和point数据
    int GoalAndPointInsert();
    //插入客观题批改信息
    int ObjMarkInsert();
}
