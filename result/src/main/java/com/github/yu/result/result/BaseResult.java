package com.github.yu.result.result;

/**
 * @author yu
 * 2022/2/20
 */
public class BaseResult  {
    private int status;
    private String message;

    public BaseResult() {
    }

    public BaseResult(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static BaseResult result(int status, String message) {
        return new BaseResult(status, message);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
