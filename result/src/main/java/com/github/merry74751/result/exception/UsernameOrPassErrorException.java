package com.github.merry74751.result.exception;

/**
 * @author yu
 * 2022/2/20
 */
public class UsernameOrPassErrorException extends RuntimeException {
    public UsernameOrPassErrorException() {
    }

    public UsernameOrPassErrorException(String message) {
        super(message);
    }
}
