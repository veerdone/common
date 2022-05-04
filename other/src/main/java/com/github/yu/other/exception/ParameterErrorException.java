package com.github.yu.other.exception;

public class ParameterErrorException extends BaseException{
    public ParameterErrorException() {
        super(ExceptionEnum.PARAMETER_ERROR);
    }

    public ParameterErrorException(String message, int status) {
        super(message, status);
    }

    public ParameterErrorException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum);
    }
}
