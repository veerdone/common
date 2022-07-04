package com.github.yu.result.advice;

import com.github.pagehelper.PageInfo;
import com.github.yu.result.result.BaseResult;
import com.github.yu.result.result.ListResult;
import com.github.yu.result.result.ObjectResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;

/**
 * @author yu
 * 2022/2/20
 */
@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return null == methodParameter.getMethodAnnotation(ExceptionHandler.class);
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof List) {
            List list = (List) o;
            PageInfo pageInfo = new PageInfo(list);
            long total = pageInfo.getTotal();
            return ListResult.result(list, total);
        } else if (o instanceof BaseResult) {
            return o;
        } else {
            return ObjectResult.result(o);
        }
    }
}
