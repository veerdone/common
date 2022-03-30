package com.github.merry74751.result.result;

/**
 * @author yu
 * 2022/2/20
 */
public class ObjectResult extends BaseResult {
    Object data;

    public ObjectResult() {
    }

    public ObjectResult(int code, String message, Object data) {
        super(code, message);
        if (null != data) {
            this.data = data;
        }
    }

    public static ObjectResult result(int code, String message, Object data) {
        return new ObjectResult(code, message, data);
    }

    public static ObjectResult success(String message, Object data) {
        return result(200, message, data);
    }

    public static ObjectResult success(Object data) {
        return result(200, "请求成功", data);
    }
}
