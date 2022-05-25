package com.github.yu.other.advice;

import com.github.pagehelper.PageHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
public class PageAspect {
    @Pointcut("execution(* com.github.yu.*.*.service.impl.*.page*(..)) || execution(* com.github.yu.*.service.impl.BaseServiceImpl.page*(..))")
    public void pointcut(){}

    @Around("pointcut()")
    public Object page(ProceedingJoinPoint joinPoint) throws Throwable {
        List<Integer> param = null;
        if ("GET".equals(getRequest().getMethod())) {
            param = getParamFromUrl();
        } else {
            param = getParamFromBody(joinPoint.getArgs()[0]);
        }
        if (param.isEmpty()) {
            param.add(1);
            param.add(10);
        }
        PageHelper.startPage(param.get(0), param.get(1));
        return joinPoint.proceed();
    }

    private HttpServletRequest getRequest() {
        RequestAttributes attribute = RequestContextHolder.getRequestAttributes();
        Assert.notNull(attribute, "RequestAttributes 为空");
        return ((ServletRequestAttributes) attribute).getRequest();
    }

    public List<Integer> getParamFromUrl() {
        HttpServletRequest request = getRequest();
        String startPage = request.getParameter("startPage");
        String pageSize = request.getParameter("pageSize");
        if (startPage != null && pageSize != null) {
            List<Integer> list = new ArrayList<>(2);
            list.add(Integer.parseInt(startPage));
            list.add(Integer.parseInt(pageSize));
            return list;
        }
        return new ArrayList<>(2);
    }

    public List<Integer> getParamFromBody(Object o) throws IllegalAccessException {
        Class<?> c = o.getClass();
        Field startPage = null;
        int i1 = 0, i2 = 0;
        try {
            startPage = c.getDeclaredField("startPage");
            startPage.setAccessible(true);
            i1 = startPage.getInt(o);

            Field pageSize = c.getDeclaredField("pageSize");
            pageSize.setAccessible(true);
            i2 = pageSize.getInt(o);
        } catch (NoSuchFieldException ignored) {

        }
        List<Integer> list = new ArrayList<>(2);
        if (i1 != 0 && i2 != 0) {
            list.add(i1);
            list.add(i2);
        }
        return list;
    }

}
