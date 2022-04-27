package com.github.yu.result.advice;

import com.github.yu.other.exception.BaseException;
import com.github.yu.result.result.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

/**
 * @author yu
 * 2022/2/20
 */
@RestControllerAdvice
public class ExceptionAdvice {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler({Exception.class, RuntimeException.class})
    public BaseResult exceptionHandler(Exception e, HttpServletResponse response) {
        logger.warn("全局异常捕获:{}", e.getMessage());
        logger.warn("", e);
        if (e instanceof BaseException) {
            response.setStatus(((BaseException) e).getStatus());
            return BaseResult.result(((BaseException) e).getStatus(), e.getMessage());
        }
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return BaseResult.result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务器异常");
    }

    @ExceptionHandler({Error.class})
    public BaseResult errorHandler(Error e, HttpServletResponse response) {
        logger.warn("全局错误捕获:{}", e.getMessage());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return BaseResult.result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务器错误");
    }
}
