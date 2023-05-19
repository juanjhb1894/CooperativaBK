package com.example.cooperativa.utils;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

public class Cypher {

    public String EncodeBase64(String text) throws UnsupportedEncodingException {
        // Sending side
        byte[] data = text.getBytes("UTF-8");
        String base64 = Base64.encodeToString(data, Base64.DEFAULT);
        return  base64;
    }

    public String DecodeBase64(String base64) throws UnsupportedEncodingException {
        // Receiving side
        byte[] data = Base64.decode(base64, Base64.DEFAULT);
        String text = new String(data, "UTF-8");
        return  text;
    }


}
