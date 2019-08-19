package com.longshihan.learnEN2.http;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorUtils {
    private ExecutorService recordExecutor;//处理数据
    private static volatile ExecutorUtils sInstance;

    private ExecutorUtils() {
        recordExecutor = Executors.newSingleThreadExecutor();
    }

    public static ExecutorUtils getInstance() {
        if (sInstance == null) {
            synchronized (ExecutorUtils.class) {
                if (sInstance == null) {
                    sInstance = new ExecutorUtils();
                }
            }
        }
        return sInstance;
    }

    public void executeRecord(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        recordExecutor.execute(runnable);
    }

}
