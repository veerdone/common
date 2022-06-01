package com.github.yu.autoconfigure.log;

import com.github.yu.base.pro.service.Service;
import com.github.yu.other.concurrent.AsyncExecutor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

/**
 * @author yu
 * 2022/5/30
 */
@Aspect
public class LogAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    private ApplicationContext context;

    @Autowired
    private AsyncExecutor asyncExecutor;


    @Pointcut("@annotation(com.github.yu.autoconfigure.log.RequestLog)")
    public void pointcut(){}

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        RequestLog requestLog = method.getAnnotation(RequestLog.class);

        Object[] args = joinPoint.getArgs();
        Object arg = args[0];
        Object id = getId(arg);

        Type type = requestLog.type();
        if (Objects.equals(type, Type.INSERT)) {
            Object result = joinPoint.proceed();
            LOGGER.info("user: {} insert: {}, value is: {}", id, arg.getClass().getName(), arg);
            return result;
        }

        Service service = context.getBean(requestLog.service());

        Object o1 = service.getById((Long) id);
        Object result = joinPoint.proceed();

        if (Objects.equals(type, Type.DELETE)) {
            LOGGER.info("user: {} delete: {}, value is: {}", id, arg.getClass().getName(), o1);
            return result;
        }

        Object o2 = service.getById((Long) id);
        handleUpdate(id, arg, o1, o2);

        return result;
    }

    private Object getId(Object o) {
        if (Objects.isNull(o)) {
            return null;
        }
        Class<?> c = o.getClass();
        try {
            Field id = c.getDeclaredField("id");
            id.setAccessible(true);
            return id.get(o);
        } catch (NoSuchFieldException e) {
            LOGGER.warn("{} don't have field id", c.getName());
        } catch (IllegalAccessException e) {
            LOGGER.warn("can't get value from field:", e);
        }
        return null;
    }

    private void handleUpdate(Object id, Object arg, Object o1, Object o2) {
        asyncExecutor.execute(() -> {
            List<Diff> list = FieldDiff.diffField(o1, o2);
            StringBuilder builder = new StringBuilder();
            for (Diff diff : list) {
                builder.append(diff);
                builder.append("\n");
            }
            LOGGER.info("user: {} update: {}, update fields are: {}", id, arg.getClass().getName(), builder.toString());
        });
    }
}
