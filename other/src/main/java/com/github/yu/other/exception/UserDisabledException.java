package com.github.yu.other.exception;

public class UserDisabledException extends BaseException{
    public UserDisabledException() {
        super(ExceptionEnum.USER_DISABLE);
    }

    public UserDisabledException(String message, int status) {
        super(message, status);
    }

    public UserDisabledException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum);
    }
}
