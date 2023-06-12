package com.wxw.java.thread;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {
    @Override
    public String call() {
        return ">>> MyCallable Callback...";
    }
}
