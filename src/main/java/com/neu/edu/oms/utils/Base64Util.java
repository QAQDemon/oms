package com.neu.edu.oms.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/*
 * @Description 对字符串进行加密解密
 **/
public class Base64Util {

    /*
     * @Description 字符串加密
     * @Param [str]
     * @return java.lang.String
     **/
    public static String encodeToString(String str){
        byte[] textByte = str.getBytes(StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(textByte);
    }

    /*
     * @Description 字符串解密
     * @Param [str]
     * @return java.lang.String
     **/
    public static String decodeToString(String str) {
        return new String(Base64.getDecoder().decode(str),StandardCharsets.UTF_8);
    }

}  