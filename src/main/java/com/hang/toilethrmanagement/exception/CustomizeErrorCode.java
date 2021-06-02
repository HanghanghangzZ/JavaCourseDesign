package com.hang.toilethrmanagement.exception;

public enum CustomizeErrorCode {
    PARAMETER_ERROR(1, "前端传入参数错误");

    private Integer code;
    private String message;

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public Integer getCode() {
        return this.code;
    }
}
