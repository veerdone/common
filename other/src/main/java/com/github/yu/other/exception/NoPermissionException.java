package com.github.yu.other.exception;

public class NoPermissionException extends BaseException{
    public NoPermissionException() {
        super(ExceptionEnum.NO_PERMISSION);
    }

    public NoPermissionException(String message, int status) {
        super(message, status);
    }

    public NoPermissionException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum);
    }
}
