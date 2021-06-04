package com.base.utils;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName  RestResponse
 * Description    封装返回数据
 * Author F
 * Date   2021/6/3 16:58
 * Version    1.0
 */
public class RestResponse extends HashMap<String, Object> {
    public RestResponse() {
        this.put("code", 200);
        this.put("message", "success");
    }

    public static RestResponse success() {
        return new RestResponse();
    }

    public static RestResponse success(String message) {
        RestResponse restResponse = new RestResponse();
        restResponse.put("message", message);
        return restResponse;
    }

    public static RestResponse success(Map<String, Object> map) {
        RestResponse restResponse = new RestResponse();
        restResponse.putAll(map);
        return restResponse;
    }

    public static RestResponse error(int code, String message) {
        RestResponse restResponse = new RestResponse();
        restResponse.put("code", code);
        restResponse.put("message", message);
        return restResponse;
    }

    public static RestResponse error(){
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "哎呀，出错了");
    }

    public static RestResponse error(String message){
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }

    @Override
    public RestResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}

