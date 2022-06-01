package com.github.yu.other.concurrent;


import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.concurrent.*;

@ConfigurationProperties(prefix = "config.async.executor")
public class AsyncExecutor {
    private Integer corePoolSize = 50;
    private Integer maximumPoolSize = 100;
    private Integer keepAliveTime = 30;
    private TimeUnit timeUnit = TimeUnit.SECONDS;
    private BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>();
    private ThreadPoolExecutor executor;

    public void execute(Runnable runnable) {
        if (this.executor == null) {
            createExecutor();
        }
        this.executor.execute(runnable);
    }

    private void createExecutor() {
        ThreadFactory factory = new TaskThreadFactory();
        this.executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, timeUnit, blockingQueue, factory);
    }

    public AsyncExecutor() { }

    public Integer getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(Integer corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public Integer getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(Integer maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public Integer getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(Integer keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public BlockingQueue<Runnable> getBlockingQueue() {
        return blockingQueue;
    }

    public void setBlockingQueue(BlockingQueue<Runnable> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public ThreadPoolExecutor getExecutor() {
        return executor;
    }

    public void setExecutor(ThreadPoolExecutor executor) {
        this.executor = executor;
    }
}
