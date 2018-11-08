package com.example.utils;


import lombok.Getter;
import lombok.Setter;

/**
 * @author gouchao
 * @since 2018.11.5 11:46
 */
@Setter
@Getter
public class ReturnModel {
    private int code;
    private String message;
    private Object data;
    public static ReturnModel success(){
        ReturnModel temp = new ReturnModel();
        temp.code = 0 ;
        temp.message = "成功！";
        return temp;
    }
    public static ReturnModel success(String message){
        ReturnModel temp = new ReturnModel();
        temp.code = 0 ;
        temp.message = message;
        return temp;
    }
    public static ReturnModel success(String message, Object data){
        ReturnModel temp = new ReturnModel();
        temp.code = 0 ;
        temp.message = message;
        temp.data = data;
        return temp;
    }
    public static ReturnModel fail(String message, Object data){
        ReturnModel temp = new ReturnModel();
        temp.code = -1 ;
        temp.message = message;
        temp.data = data;
        return temp;
    }

    public static ReturnModel fail(String message){
        ReturnModel temp = new ReturnModel();
        temp.code = -1 ;
        temp.message = message;
        return temp;
    }
}
