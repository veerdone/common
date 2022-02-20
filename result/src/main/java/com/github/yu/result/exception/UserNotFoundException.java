package com.github.yu.result.exception;

/**
 * @author yu
 * 2022/2/20
 */
public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
