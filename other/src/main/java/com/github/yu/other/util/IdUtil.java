package com.github.yu.other.util;

import cn.hutool.core.lang.Snowflake;

public class IdUtil {
    private static final Snowflake SNOWFLAKE = cn.hutool.core.util.IdUtil.getSnowflake();

    public static Long getSnowId() {
        return SNOWFLAKE.nextId();
    }

    public static Long getSnowId(long workerId, long datacenterId) {
        return cn.hutool.core.util.IdUtil.getSnowflake(workerId, datacenterId).nextId();
    }

    public static Long getSnowId(long workerId) {
        return cn.hutool.core.util.IdUtil.getSnowflake(workerId).nextId();
    }

    public static String simpleUUID() {
        return cn.hutool.core.util.IdUtil.simpleUUID();
    }

    public static String UUID() {
        return cn.hutool.core.util.IdUtil.randomUUID();
    }
}
