package com.example.demoapi.exception;

public enum ErrorCode {
    ERR_NOT_FOUND("ERR10010"),
    ERR_PUT("ERR10011"),
    ERR_PATCH("ERR10012"),
    ERR_DELETE("ERR10013");

    public final String code;

    private ErrorCode(String code) {
        this.code = code;
    }
}
