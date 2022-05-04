package com.github.yu.other.exception;

public class PhoneExistException extends BaseException{
    public PhoneExistException() {
        super(ExceptionEnum.TELEPHONE_EXIST);
    }

    public PhoneExistException(String message, int status) {
        super(message, status);
    }

    public PhoneExistException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum);
    }
}
