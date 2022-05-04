package com.github.yu.other.exception;

public class UserNotFoundException extends BaseException{
    public UserNotFoundException() {
        super(ExceptionEnum.USER_NOT_FOUND);
    }

    public UserNotFoundException(String message, int status) {
        super(message, status);
    }

    public UserNotFoundException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum);
    }
}
