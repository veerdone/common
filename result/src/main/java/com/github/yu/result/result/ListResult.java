package com.github.yu.result.result;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.yu.result.LongSerializer;

import java.util.List;

/**
 * @author yu
 * 2022/2/20
 */
public class ListResult extends BaseResult{
    private List list;

    @JsonSerialize(using = LongSerializer.class)
    private Long total;

    public ListResult() {
    }

    public ListResult(int code, String message, List list, Long total) {
        super(code, message);
        this.list = list;
        this.total = total;
    }

    public static ListResult result(List list, Long total) {
        return new ListResult(200, "请求成功", list, total);
    }


    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
