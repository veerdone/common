package com.github.merry74751.result.exception;

/**
 * @author yu
 * 2022/2/20
 */
public class UnAuthException extends RuntimeException {
    public UnAuthException() {
    }

    public UnAuthException(String message) {
        super(message);
    }
}
