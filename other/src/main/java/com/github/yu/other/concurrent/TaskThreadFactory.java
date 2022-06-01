package com.github.yu.other.concurrent;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yu
 * 2022/5/30
 */
public class TaskThreadFactory implements ThreadFactory {
    private final AtomicInteger nextId = new AtomicInteger();

    @Override
    public Thread newThread(Runnable runnable) {
        int id = nextId.getAndIncrement();
        String name = "From TaskThreadFactory's" + " -executor-" + id;
        return new Thread(null, runnable, name, 0);
    }
}
