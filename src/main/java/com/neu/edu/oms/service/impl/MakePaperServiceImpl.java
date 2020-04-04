package com.neu.edu.oms.service.impl;

import com.neu.edu.oms.dao.SubjectMapper;
import com.neu.edu.oms.entity.Subject;
import com.neu.edu.oms.service.MakePaperService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;


@Service
public class MakePaperServiceImpl implements MakePaperService {

    @Resource
    SubjectMapper subjectMapper;

    /*
     * @Description 获得科目
     * @Param []
     * @return java.util.List<com.neu.edu.oms.entity.Subject>
     **/
    public List<Subject> getSubjectList() {
        return subjectMapper.selectAll();
    }
}
