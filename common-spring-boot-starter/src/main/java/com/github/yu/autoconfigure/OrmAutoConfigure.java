package com.github.yu.autoconfigure;

import com.github.yu.base.interceptor.DateInterceptor;
import com.github.yu.base.pro.config.TimeHandler;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author yu
 * 2022/5/25
 */
@AutoConfiguration
@ConditionalOnProperty(prefix = "config.auto", name = "orm", havingValue = "true", matchIfMissing = true)
@Import({OrmAutoConfigure.DateInterceptorConfigure.class, OrmAutoConfigure.TimeHandlerConfigure.class})
public class OrmAutoConfigure {

    @Configuration
    @ConditionalOnClass(name = "com.github.yu.base.mapper.BaseMapper")
    static class DateInterceptorConfigure {
        @Bean
        public DateInterceptor dateInterceptor() {
            return new DateInterceptor();
        }
    }

    @Configuration
    @ConditionalOnClass(name = "com.github.yu.base.pro.mapper.Mapper")
    static class TimeHandlerConfigure {
        @Bean
        public TimeHandler timeHandler() {
            return new TimeHandler();
        }
    }

}
