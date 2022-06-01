package com.github.yu.autoconfigure.log;

import com.github.yu.base.pro.service.Service;

import java.lang.annotation.*;

/**
 * @author yu
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface RequestLog {
    Class<? extends Service> service();

    Type type() default Type.INSERT;
}
