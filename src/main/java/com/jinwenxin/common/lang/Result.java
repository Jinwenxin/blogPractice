package com.jinwenxin.common.lang;

import lombok.Data;

import java.io.Serializable;
import java.util.Locale;

/**
 * 封装结果类
 */
@Data
public class Result implements Serializable {

    private int code;
    private String msg;
    private Object data;

    /**
     * 成功的返回
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public static Result succ(int code,String msg, Object data){
        Result r =new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static Result fail(int code,String msg, Object data){
        Result r =new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static Result succ(Object data){
        return Result.succ(200,"操作成功",data);
    }

    public static Result fail(String msg, Object data){
        return fail(400, msg,data);
    }

    public static Result fail(String msg){
        return fail(400,msg,null);
    }
}
