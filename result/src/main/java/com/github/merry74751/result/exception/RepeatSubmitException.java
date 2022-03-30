package com.github.merry74751.result.exception;

/**
 * @author yu
 * 2022/2/20
 */
public class RepeatSubmitException extends RuntimeException{
    public RepeatSubmitException() {
    }

    public RepeatSubmitException(String message) {
        super(message);
    }
}
