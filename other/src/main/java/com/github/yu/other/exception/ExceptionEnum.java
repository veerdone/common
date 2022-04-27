package com.github.yu.other.exception;

public enum ExceptionEnum {
    USER_NOT_FOUND(400, "用户不存在"),
    ACCOUNT_EXIST(400, "账号已存在"),
    TELEPHONE_EXIST(400, "手机号已注册"),
    ACCOUNT_OR_PASSWORD_ERROR(400, "用户名或密码错误"),
    USER_DISABLE(403, "用户被禁用"),
    PARAMETER_ERROR(400, "参数错误"),
    USERNAME_EXIST(400, "用户名已存在"),
    FILE_SUFFIX_ERROR(400, "文件格式不合法"),
    PASSWORD_ERROR_EXCEPTION(400, "密码错误");

    ExceptionEnum(int status, String message) {
        this.status = status;
        this.message = message;
    }

    private final int status;

    private final String message;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
