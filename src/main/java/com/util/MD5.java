package com.util;

import org.apache.tomcat.util.codec.binary.Base64;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MD5 {

    private static final String ALGORITHM = "MD5";

    public static String encode(String param) {

        MessageDigest digest = null;

        String encode = null;

        try {

            digest = MessageDigest.getInstance("MD5");

            BASE64Encoder base64Encoder = new BASE64Encoder();

            encode = base64Encoder.encode(digest.digest(param.getBytes("UTF-8")));

        } catch (NoSuchAlgorithmException e) {
            System.out.println("获取MD5实例失败");
        } catch (UnsupportedEncodingException e) {
            System.out.println("编码异常");
        }

        return encode;
    }
}
