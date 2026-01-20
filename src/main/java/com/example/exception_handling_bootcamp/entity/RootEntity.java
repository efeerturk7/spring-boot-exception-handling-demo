package com.example.exception_handling_bootcamp.entity;

import lombok.Data;

@Data
public class RootEntity <T>{
    private boolean result;
    private T data;
    private String message;
    public static <T> RootEntity  <T> ok(T data){
        RootEntity<T>rootEntity=new RootEntity<>();
        rootEntity.setResult(true);
        rootEntity.setData(data);
        rootEntity.setMessage("successful");
        return rootEntity;
    }
    public static <T> RootEntity  <T> error(T data,String message){
        RootEntity<T>rootEntity=new RootEntity<>();
        rootEntity.setData(data);
        rootEntity.setMessage(message);
        rootEntity.setResult(false);
        return rootEntity;
    }
}
