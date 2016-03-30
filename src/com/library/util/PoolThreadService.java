package com.library.util;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by gfy on 2016/3/26.
 */
public class PoolThreadService {
    private static Handler mMainHandler = new Handler(Looper.getMainLooper());
    private static ExecutorService  executor;

    static {
        final AtomicInteger thread_index = new AtomicInteger();
        executor = Executors.newCachedThreadPool(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r,"my_thread:"+thread_index.getAndIncrement());
            }
        });
    }

    public static void execute(Runnable runnable){
        executor.execute(runnable);
    }

    public Future<?> submit(Runnable runnable){
        return executor.submit(runnable);
    }

    public void post(Runnable runnable){
        mMainHandler.post(runnable);
    }

    public static void destroy(){
        shutdownAndAwaitTermination(executor);
    }

    //The following method shuts down an {@code ExecutorService} in two phases,
    //first by calling {@code shutdown} to reject incoming tasks, and then
    //calling {@code shutdownNow}, if necessary, to cancel any lingering tasks:
    public static void shutdownAndAwaitTermination(ExecutorService pool) {
        pool.shutdown(); // Disable new tasks from being submitted
        try {
            // Wait a while for existing tasks to terminate
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                pool.shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!pool.awaitTermination(60, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ie) {
            // (Re-)Cancel if current thread also interrupted
            pool.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }
}
