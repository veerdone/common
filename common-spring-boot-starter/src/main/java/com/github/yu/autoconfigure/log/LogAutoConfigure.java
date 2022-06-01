package com.github.yu.autoconfigure.log;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

/**
 * @author yu
 * 2022/5/30
 */
@AutoConfiguration
@ConditionalOnProperty(prefix = "config.auto", name = "log", havingValue = "true", matchIfMissing = true)
public class LogAutoConfigure {
    @Bean
    @ConditionalOnClass(name = "com.github.yu.base.pro.mapper.Mapper")
    public LogAspect logAspect() {
        return new LogAspect();
    }

    @Bean
    @ConditionalOnClass(name = "com.github.yu.base.mapper.BaseMapper")
    public Log2Aspect log2Aspect() {
        return new Log2Aspect();
    }
}
