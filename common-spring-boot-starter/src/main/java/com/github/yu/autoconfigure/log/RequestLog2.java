package com.github.yu.autoconfigure.log;

import com.github.yu.base.service.BaseService;

import java.lang.annotation.*;

/**
 * @author yu
 * 2022/5/30
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestLog2 {
    Class<? extends BaseService> service();

    Type type() default Type.INSERT;
}
