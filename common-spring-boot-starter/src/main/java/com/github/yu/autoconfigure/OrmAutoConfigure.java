package com.github.yu.autoconfigure;

import com.github.yu.base.interceptor.DateInterceptor;
import com.github.yu.base.pro.config.TimeHandler;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

/**
 * @author yu
 * 2022/5/25
 */
@AutoConfiguration
@ConditionalOnProperty(prefix = "config.auto", name = "orm", havingValue = "true", matchIfMissing = true)
public class OrmAutoConfigure {

    @Bean
    @ConditionalOnClass(DateInterceptor.class)
    public DateInterceptor dateInterceptor() {
        return new DateInterceptor();
    }

    @Bean
    @ConditionalOnClass(TimeHandler.class)
    public TimeHandler timeHandler() {
        return new TimeHandler();
    }
}
