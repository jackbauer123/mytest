package com.account;

public class CatchExceptionInfo {


    private Throwable exception;

    private String name;

    public CatchExceptionInfo(Throwable exception,String name){
        this.exception=exception;
        this.name=name;
    }

    public CatchExceptionInfo(){}

    public Throwable getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
