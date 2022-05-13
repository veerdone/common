package com.github.yu.other.exception;

public class UserDisableException extends BaseException{
    public UserDisableException() {
        super(ExceptionEnum.USER_DISABLE);
    }

    public UserDisableException(String message, int status) {
        super(message, status);
    }

    public UserDisableException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum);
    }
}
