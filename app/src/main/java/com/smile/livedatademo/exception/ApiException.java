package com.smile.livedatademo.exception;

public class ApiException extends Exception {
    private int code;
    private String msg;

    public ApiException(Throwable throwable,int code) {
        super(throwable);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
