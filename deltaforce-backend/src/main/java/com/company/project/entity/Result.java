package com.company.project.entity;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

@Data
public class Result<T>  {

    private Integer code;
    private String msg;
    private T data;

    public <T> Result<T> success(T object) {
        Result<T> result = new Result<>();
        result.data = object;
        result.code = 200;
        return result;
    }

    public <T> Result<T> success(T object,String msg) {
        Result<T> result = new Result<>();
        result.data = object;
        result.code = 200;
        result.msg = msg;
        return result;
    }

    public <T> Result error(String msg) {
        Result result = new Result();
        result.msg = msg;
        result.code = 500;
        return result;
    }

    public <T> Result error(T object,String msg) {
        Result result = new Result();
        result.msg = msg;
        result.code = 500;
        result.data = object;
        return result;
    }
    public <T> Result updateCookie(String msg) {
        Result result = new Result();
        result.msg = msg;
        result.code = 300;
        return result;
    }

    public <T> Result updateCookie(T object,String msg) {
        Result result = new Result();
        result.msg = msg;
        result.code = 300;
        result.data = object;
        return result;
    }
    public JSONObject toJson() {
        return JSONObject.parseObject(JSONObject.toJSONString(this));
    }

    public String toJsonString() {
        return JSONObject.toJSONString(this);
    }
}
