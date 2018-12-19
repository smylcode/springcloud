package com.example.common.utils;


import lombok.Getter;
import lombok.Setter;

/**
 * @author gouchao
 * @since 2018.11.5 11:46
 */
@Setter
@Getter
public class ReturnModel {
    //返回码：0成功，-1 失败，-2 token失效, -3 非法请求
    private int code;
    private String des;
    private Object data;
    public static ReturnModel success(){
        ReturnModel temp = new ReturnModel();
        temp.code = 0 ;
        temp.des = "成功！";
        return temp;
    }
    public static ReturnModel success(String message){
        ReturnModel temp = new ReturnModel();
        temp.code = 0 ;
        temp.des = message;
        return temp;
    }
    public static ReturnModel success(Object data){
        ReturnModel temp = new ReturnModel();
        temp.code = 0 ;
        temp.data = data;
        return temp;
    }
    public static ReturnModel success(String message, Object data){
        ReturnModel temp = new ReturnModel();
        temp.code = 0 ;
        temp.des = message;
        temp.data = data;
        return temp;
    }
    public static ReturnModel fail(String message, Object data){
        ReturnModel temp = new ReturnModel();
        temp.code = -1 ;
        temp.des = message;
        temp.data = data;
        return temp;
    }

    public static ReturnModel fail(String message){
        ReturnModel temp = new ReturnModel();
        temp.code = -1 ;
        temp.des = message;
        return temp;
    }

    public static ReturnModel fail(int code, String message){
        ReturnModel temp = new ReturnModel();
        temp.code = code;
        temp.des = message;
        return temp;
    }
}
