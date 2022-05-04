package com.github.yu.other.exception;

public class UsernameExistException extends BaseException{
    public UsernameExistException() {
        super(ExceptionEnum.USERNAME_EXIST);
    }

    public UsernameExistException(String message, int status) {
        super(message, status);
    }

    public UsernameExistException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum);
    }
}
