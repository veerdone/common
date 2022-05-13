package com.github.yu.other.concurrent;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
@ConfigurationProperties(prefix = "executor")
public class AsyncExecutor implements BeanPostProcessor {
    private Integer corePoolSize;
    private Integer maximumPoolSize;
    private Integer keepAliveTime;
    private TimeUnit timeUnit;
    private BlockingQueue<Runnable> blockingQueue;
    private ThreadPoolExecutor executor;

    private static final ThreadPoolExecutor EXECUTOR =
            new ThreadPoolExecutor(40, 100, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

    public void executor(Runnable runnable) {
        EXECUTOR.execute(runnable);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof AsyncExecutor) {
            ((AsyncExecutor) bean).executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, timeUnit, blockingQueue);
        }
        return bean;
    }
}
