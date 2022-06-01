package com.github.yu.autoconfigure.log;

/**
 * @author yu
 * 2022/5/30
 */
public class ClassDifferentException extends RuntimeException {
    public ClassDifferentException() {
    }

    public ClassDifferentException(String message) {
        super(message);
    }
}
