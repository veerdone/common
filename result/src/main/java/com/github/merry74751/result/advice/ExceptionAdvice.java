package com.github.merry74751.result.advice;

import com.github.merry74751.result.exception.RepeatSubmitException;
import com.github.merry74751.result.exception.UnAuthException;
import com.github.merry74751.result.exception.UserNotFoundException;
import com.github.merry74751.result.exception.UsernameOrPassErrorException;
import com.github.merry74751.result.result.BaseResult;
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
        BaseResult result = handler(e, response);
        if (null != result) {
            return result;
        }
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return BaseResult.result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务器异常");
    }

    @ExceptionHandler({Error.class})
    public BaseResult errorHandler(Error e, HttpServletResponse response) {
        logger.warn("全局错误捕获:{}", e.getMessage());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return BaseResult.result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务器异常");
    }

    private BaseResult handler(Exception e, HttpServletResponse response) {
        if (e instanceof UsernameOrPassErrorException) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            return BaseResult.result(HttpStatus.FORBIDDEN.value(), e.getMessage());
        } else if (e instanceof UnAuthException) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return BaseResult.result(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
        } else if (e instanceof UserNotFoundException) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            return BaseResult.result(HttpStatus.FORBIDDEN.value(), e.getMessage());
        } else if (e instanceof RepeatSubmitException) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            return BaseResult.result(HttpStatus.FORBIDDEN.value(), e.getMessage());
        }
        return null;
    }
}
