package com.smile.livedatademo.utils;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class NetUtils {

    private static final String TYPE="application/json; charset=utf-8";

    public static RequestBody ParamsToBody(Object o){
        Gson gson=new Gson();
        String json=gson.toJson(o);
        RequestBody body=RequestBody.create(MediaType.parse(TYPE), json);
        return body;
    }
}
