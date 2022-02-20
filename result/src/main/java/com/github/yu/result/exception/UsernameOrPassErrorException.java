package com.github.yu.result.exception;

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
