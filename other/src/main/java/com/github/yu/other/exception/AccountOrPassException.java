package com.github.yu.other.exception;

public class AccountOrPassException extends BaseException{
    public AccountOrPassException() {
        super(ExceptionEnum.ACCOUNT_OR_PASSWORD_ERROR);
    }

    public AccountOrPassException(String message, int status) {
        super(message, status);
    }

    public AccountOrPassException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum);
    }
}
