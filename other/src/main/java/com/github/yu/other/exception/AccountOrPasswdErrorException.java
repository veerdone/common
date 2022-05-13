package com.github.yu.other.exception;

public class AccountOrPasswdErrorException extends BaseException{
    public AccountOrPasswdErrorException() {
        super(ExceptionEnum.ACCOUNT_OR_PASSWORD_ERROR);
    }

    public AccountOrPasswdErrorException(String message, int status) {
        super(message, status);
    }

    public AccountOrPasswdErrorException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum);
    }
}
