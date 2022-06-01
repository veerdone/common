package com.github.yu.result.result;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author yu
 * 2022/2/20
 */
public class ObjectResult extends BaseResult {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    public ObjectResult() {
    }

    public ObjectResult(int code, String message, Object data) {
        super(code, message);
        this.data = data;
    }

    public static ObjectResult result(Object data) {
        return new ObjectResult(200, "请求成功", data);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
