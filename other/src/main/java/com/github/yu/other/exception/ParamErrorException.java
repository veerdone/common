package com.github.yu.other.exception;

public class ParamErrorException extends BaseException{
    public ParamErrorException() {
        super(ExceptionEnum.PARAMETER_ERROR);
    }

    public ParamErrorException(String message, int status) {
        super(message, status);
    }

    public ParamErrorException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum);
    }
}
