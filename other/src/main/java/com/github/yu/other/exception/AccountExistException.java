package com.github.yu.other.exception;

public class AccountExistException extends BaseException{
    public AccountExistException() {
        super(ExceptionEnum.ACCOUNT_EXIST);
    }

    public AccountExistException(String message, int status) {
        super(message, status);
    }

    public AccountExistException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum);
    }
}
