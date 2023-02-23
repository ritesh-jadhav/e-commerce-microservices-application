package com.boot.exception;


public class ProductServiceCustomException extends  RuntimeException{
    private String errorCode;
    ProductServiceCustomException(String msg,String errorCode){
        super(msg);
        this.errorCode = errorCode;
    }
}
