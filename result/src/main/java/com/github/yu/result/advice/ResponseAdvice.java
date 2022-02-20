package com.github.yu.result.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        if (null != methodParameter.getMethodAnnotation(ExceptionHandler.class)) {
            return false;
        }
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o != null) {
            ObjectMapper mapper = new ObjectMapper();
            if (o instanceof List) {
                try {
                    return mapper.writeValueAsString(ListResult.success((List)o));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
            try {
                return mapper.writeValueAsString(ObjectResult.success(o));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return o;
    }
}
