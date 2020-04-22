package com.neu.edu.oms.service.impl;

import com.neu.edu.oms.dao.ObjMarkMapper;
import com.neu.edu.oms.dao.PaperScanMapper;
import com.neu.edu.oms.service.ScanAndMarkObjService;
import com.sun.corba.se.impl.util.PackagePrefixChecker;
import org.apache.http.entity.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/*
 * @Description
 * @author demon
 * @version v1.0
 **/
@SpringBootTest
class ScanAndMarkObjServiceImplTest {

    @Resource
    ScanAndMarkObjService scanAndMarkObjService;
    @Resource
    PaperScanMapper paperScanMapper;
    @Resource
    ObjMarkMapper objMarkMapper;

    @Test
    void saveScanImg() throws IOException {
        MultipartFile[] files=new MultipartFile[2];
        File file = new File("C:\\Users\\demon\\Desktop\\work2\\aj.jpg");
        FileInputStream fileInputStream = new FileInputStream(file);
        files[0]=  new MockMultipartFile("1.jpg",file.getName(), ContentType.APPLICATION_OCTET_STREAM.toString(),fileInputStream);
        file=new File("C:\\Users\\demon\\Desktop\\work2\\aj2.jpg");
        fileInputStream = new FileInputStream(file);
        files[1]=  new MockMultipartFile("2.jpg",file.getName(), ContentType.APPLICATION_OCTET_STREAM.toString(),fileInputStream);
        List<String> list=scanAndMarkObjService.saveScanImg(files);
        return;
    }

    @Test
    void setScanAndObj() {
        String str="{\"20160000_1_1\":[\"1000\",\"0100\",\"10\",\"11\"],\"20160001_1_1\":[\"1000\",\"1010\",\"10\",\"01\"]}";
        scanAndMarkObjService.setScanAndObj(str,1);
    }

    @Test
    void tes(){
        int[] paperScanIds=paperScanMapper.selectByThreeId(1, 1, 20160000);
        for (int id : paperScanIds) {
            objMarkMapper.deleteByPaperScanId(id);
            paperScanMapper.deleteByPrimaryKey(id);
        }
        return;
    }
}