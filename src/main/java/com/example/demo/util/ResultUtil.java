package com.example.demo.util;

import com.example.demo.domain.Result;

public class ResultUtil {

    public static Result success(Object o){
        Result result = new Result();
        result.setCode(200);
        result.setMessage("success");
        result.setData(o);
        return result;
    }

    public static Result success(){
         return success(null);
    }

    public static Result error(Integer code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }
}
