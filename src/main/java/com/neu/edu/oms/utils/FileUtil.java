package com.neu.edu.oms.utils;

import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/*
 * @Description
 * @author demon
 * @version v1.0
 **/
public class FileUtil {

    /*
     * @Description 保存文件到本地
     * @Param [excelFile，path保存路径包含文件名]
     * @return java.lang.Boolean true成功 false失败
     **/
    public static Boolean saveFile(MultipartFile file, String path){
        if (file.isEmpty()) {
            return false;
        }
        File dest = new File(path);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest); // 保存文件
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     * @Description 根据路径读取文件
     * @Param [path 加上文件名]
     * @return byte[]
     **/
    public static byte[] getFileStream(String path){
        try {
            FileInputStream inputStream = new FileInputStream(new File(path));
            byte[] bytes= new byte[inputStream.available()];
            inputStream.read(bytes,0,inputStream.available());
            return bytes;
        } catch (IOException e) {
            return null;
        }
    }
}
