package com.github.yu.base.interceptor;


import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Intercepts({@Signature(method = "update", type = Executor.class, args = {MappedStatement.class, Object.class})})
@Component
public class DateInterceptor implements Interceptor {
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
                    Field field = c.getDeclaredField("createTime");
                    setValue(field, arg);
                } else if (SqlCommandType.UPDATE == sqlCommandType) {
                    Field field = c.getDeclaredField("updateTime");
                    setValue(field, arg);
                }
            }
        }
        return invocation.proceed();
    }

    private void setValue(Field field, Object arg) throws IllegalAccessException {
        field.setAccessible(true);
        DateField dateField = field.getAnnotation(DateField.class);
        if (dateField.value() == DateFieldEnum.LOCAL_DATE_TIME) {
            field.set(arg, LocalDateTime.now());
        } else if (dateField.value() == DateFieldEnum.LOCAL_DATE) {
            field.set(arg, LocalDateTime.now());
        } else {
            field.set(arg, LocalTime.now());
        }
    }
}
