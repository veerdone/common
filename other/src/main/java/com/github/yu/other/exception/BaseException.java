package com.github.yu.other.exception;

public class BaseException extends RuntimeException{
    private ExceptionEnum exceptionEnum;
    private int status;

    public BaseException() {}

    public BaseException(String message, int status) {
        super(message);
        this.status = status;
    }

    public BaseException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.status = exceptionEnum.getStatus();
    }

    public ExceptionEnum getExceptionEnum() {
        return exceptionEnum;
    }

    public void setExceptionEnum(ExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
