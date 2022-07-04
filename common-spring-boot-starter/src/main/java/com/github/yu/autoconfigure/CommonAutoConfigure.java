package com.github.yu.autoconfigure;

import com.github.yu.other.concurrent.AsyncExecutor;
import com.github.yu.other.util.BCryptPasswordEncoder;
import com.github.yu.other.util.JwtUtil;
import com.github.yu.result.advice.ExceptionAdvice;
import com.github.yu.result.advice.ResponseAdvice;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * @author yu
 * 2022/5/24
 */
@AutoConfiguration
@ConditionalOnProperty(prefix = "config.auto", name = "common", havingValue = "true", matchIfMissing = true)
@Import({ResponseAdvice.class})
@EnableConfigurationProperties(AsyncExecutor.class)
public class CommonAutoConfigure {

    @Bean
    @ConditionalOnMissingBean(ExceptionAdvice.class)
    public ExceptionAdvice exceptionAdvice() {
        return new ExceptionAdvice();
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
