package com.hang.toilethrmanagement.exception;

public class CustomizeException extends RuntimeException {
    private String message;
    private Integer code;

    public CustomizeException(CustomizeErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public String getMessage() {
        return this.message;
    }

    public Integer getCode() {
        return code;
    }
}
