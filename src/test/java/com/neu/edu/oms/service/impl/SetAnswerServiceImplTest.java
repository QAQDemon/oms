package com.neu.edu.oms.service.impl;

import com.neu.edu.oms.service.SetAnswerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import org.apache.http.entity.ContentType;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/*
 * @Description
 * @author demon
 * @version v1.0
 **/
@SpringBootTest
@Transactional
class SetAnswerServiceImplTest {

    @Resource
    SetAnswerService setAnswerService;

    @Test
    void setSubjAnswerAndSaveImgFiles1() throws IOException {
        MultipartFile[] files=new MultipartFile[2];
        File file = new File("C:\\Users\\demon\\Desktop\\work2\\aj.jpg");
        FileInputStream fileInputStream = new FileInputStream(file);
        files[0]=  new MockMultipartFile("1.jpg",file.getName(), ContentType.APPLICATION_OCTET_STREAM.toString(),fileInputStream);
        file=new File("C:\\Users\\demon\\Desktop\\work2\\aj2.jpg");
        fileInputStream = new FileInputStream(file);
        files[1]=  new MockMultipartFile("2.jpg",file.getName(), ContentType.APPLICATION_OCTET_STREAM.toString(),fileInputStream);
        Assertions.assertTrue(setAnswerService.setSubjAnswerAndSaveImgFiles("1",files));
    }

    @Test
    void setSubjAnswerAndSaveImgFiles2() throws IOException {
        MultipartFile[] files=new MultipartFile[2];
        File file = new File("C:\\Users\\demon\\Desktop\\work2\\aj.jpg");
        FileInputStream fileInputStream = new FileInputStream(file);
        files[0]=  new MockMultipartFile("1.jpg",file.getName(), ContentType.APPLICATION_OCTET_STREAM.toString(),fileInputStream);
        file=new File("C:\\Users\\demon\\Desktop\\work2\\aj2.jpg");
        fileInputStream = new FileInputStream(file);
        files[1]=  new MockMultipartFile("2.jpg",file.getName(), ContentType.APPLICATION_OCTET_STREAM.toString(),fileInputStream);
        Assertions.assertTrue(setAnswerService.setSubjAnswerAndSaveImgFiles("[{\"subjAnswerId\":1,\"answerId\":1,\"pointId\":1,\"goalId\":1,\"questionNum\":1,\"score\":11,\"pointDescription\":\"as\",\"pointPhoto\":\"as\"},{\"answerId\":1,\"pointId\":1,\"goalId\":1,\"questionNum\":6,\"score\":11,\"pointDescription\":\"as\"}]",files));
    }

    @Test
    void test(){
        String s = "1.jpg";
        s=s.split("\\.")[0];
        return;
    }
}