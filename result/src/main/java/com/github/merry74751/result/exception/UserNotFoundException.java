package com.github.merry74751.result.exception;

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
