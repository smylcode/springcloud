package com.example.common.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

/**
 * @author gouchao
 * @since 2018.11.27 17:15
 */
public class Md5util {
    //统一加密后缀，不能更改
    private static String code = "109^*_901^|*)_(^~^)";
    private static int tempLength = 30;

    public static String encryptByMD5(String str) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        try {
            str += code;
            str = DigestUtils.md5Hex(str) + DigestUtils.sha1Hex(str).substring(tempLength);
            return str;
        } catch (Exception e) {
            return str.trim();
        }
    }

    public static void main(String []args){
        String str = encryptByMD5("000000");
        System.out.println("密文：" + str);
        System.out.println("密文长度" + str.length());
    }
}
