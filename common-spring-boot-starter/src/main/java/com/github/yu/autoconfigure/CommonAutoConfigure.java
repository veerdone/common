package com.github.yu.autoconfigure;

import com.github.yu.other.concurrent.AsyncExecutor;
import com.github.yu.other.util.BCryptPasswordEncoder;
import com.github.yu.other.util.JwtUtil;
import com.github.yu.result.advice.ExceptionAdvice;
import com.github.yu.result.advice.ResponseAdvice;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author yu
 * 2022/5/24
 */
@AutoConfiguration
@ConditionalOnProperty(prefix = "config.auto", name = "common", havingValue = "true", matchIfMissing = true)
public class CommonAutoConfigure {

    @Bean
    @ConditionalOnMissingBean(ResponseBodyAdvice.class)
    public ResponseBodyAdvice<Object> responseBodyAdvice() {
        return new ResponseAdvice();
    }

    @Bean
    @ConditionalOnMissingBean(ExceptionAdvice.class)
    public ExceptionAdvice exceptionAdvice() {
        return new ExceptionAdvice();
    }

    @Bean
    @ConditionalOnMissingBean(AsyncExecutor.class)
    public AsyncExecutor asyncExecutor() {
        return new AsyncExecutor();
    }

    @Bean
    @ConditionalOnMissingBean(JwtUtil.class)
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }

    @Bean
    @ConditionalOnMissingBean(BCryptPasswordEncoder.class)
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
