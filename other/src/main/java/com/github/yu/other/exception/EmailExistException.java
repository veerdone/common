package com.github.yu.other.exception;

/**
 * @author yu
 * 2022/5/29
 */
public class EmailExistException extends BaseException {
    public EmailExistException() {
        super(ExceptionEnum.EMAIL_EXIST);
    }

    public EmailExistException(String message, int status) {
        super(message, status);
    }

    public EmailExistException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum);
    }
}
