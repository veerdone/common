package com.github.yu.autoconfigure.log;

import java.util.StringJoiner;

/**
 * @author yu
 * 2022/5/30
 */
public class Diff {
    private String fieldName;
    private Object beforeValue;
    private Object afterValue;

    public Diff() {
    }

    public Diff(String fieldName, Object beforeValue, Object afterValue) {
        this.fieldName = fieldName;
        this.beforeValue = beforeValue;
        this.afterValue = afterValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object getBeforeValue() {
        return beforeValue;
    }

    public void setBeforeValue(Object beforeValue) {
        this.beforeValue = beforeValue;
    }

    public Object getAfterValue() {
        return afterValue;
    }

    public void setAfterValue(Object afterValue) {
        this.afterValue = afterValue;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "{", "}")
                .add("fieldName='" + fieldName + "'")
                .add("beforeValue=" + beforeValue)
                .add("afterValue=" + afterValue)
                .toString();
    }
}
