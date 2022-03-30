package com.github.merry74751.result.result;

import java.util.List;

/**
 * @author yu
 * 2022/2/20
 */
public class ListResult extends BaseResult{
    private List list;

    public ListResult() {
    }

    public ListResult(int code, String message, List list) {
        super(code, message);
        this.list = list;
    }

    public static ListResult result(int code, String message, List list) {
        return new ListResult(code, message, list);
    }

    public static ListResult success(String message, List list) {
        return result(200, message, list);
    }

    public static ListResult success(List list) {
        return result(200, "请求成功", list);
    }
}
