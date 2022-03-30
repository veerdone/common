package com.github.merry74751.result.result;

/**
 * @author yu
 * 2022/2/20
 */
public class BaseResult  {
    private int code;
    private String message;

    public BaseResult() {
    }

    public BaseResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static BaseResult result(int code, String message) {
        return new BaseResult(code, message);
    }
}
