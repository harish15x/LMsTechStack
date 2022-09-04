package com.bridgelabz.lmstechstack.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class TechStackNotFoundException extends  RuntimeException{
    private int statusCode;
    private String statusMessage;
    public TechStackNotFoundException(  int statusCode, String statusMessage){
        super(statusMessage);
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }
}
