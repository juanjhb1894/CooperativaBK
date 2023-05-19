package com.example.cooperativa.utils;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

public class Decryption {

    public static String Base64(String base64) throws UnsupportedEncodingException {
        byte[] data = Base64.decode(base64, Base64.DEFAULT);
        String text = new String(data, "UTF-8");
        return text;
    }


}
