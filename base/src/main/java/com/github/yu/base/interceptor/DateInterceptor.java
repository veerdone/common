package com.github.yu.base.interceptor;


import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author yu
 */
@Intercepts({@Signature(method = "update", type = Executor.class, args = {MappedStatement.class, Object.class})})
public class DateInterceptor implements Interceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        SqlCommandType sqlCommandType = null;
        for (Object arg : args) {
            if (arg instanceof MappedStatement) {
                MappedStatement statement = (MappedStatement) arg;
                sqlCommandType = statement.getSqlCommandType();
                continue;
            }

            Class<?> c = arg.getClass();
            if (c.getAnnotation(DateClsInterceptor.class) != null) {
                if (SqlCommandType.INSERT == sqlCommandType) {
                    setValue(c, "createTime", arg);
                } else if (SqlCommandType.UPDATE == sqlCommandType) {
                    setValue(c, "updateTime", arg);
                }
            }
        }
        return invocation.proceed();
    }

    private void setValue(Class<?> c, String fieldName, Object arg)  {
        try {
            Field field = c.getDeclaredField(fieldName);
            field.setAccessible(true);
            DateField dateField = field.getAnnotation(DateField.class);
            if (null == dateField) {
                field.set(arg, LocalDateTime.now());
                return;
            }
            if (dateField.value() == DateFieldEnum.LOCAL_DATE) {
                field.set(arg, LocalDate.now());
            } else {
                field.set(arg, LocalTime.now());
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            LOGGER.info("{} doesn't have field {}", c.getName(), fieldName);
        }
    }
}
