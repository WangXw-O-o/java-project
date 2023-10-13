package com.wxw.java.thread.pool;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {
    @Override
    public String call() {
        return ">>> MyCallable Callback...";
    }
}
