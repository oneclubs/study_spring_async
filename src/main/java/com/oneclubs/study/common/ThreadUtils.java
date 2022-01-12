package com.oneclubs.study.common;

public final class ThreadUtils {

    ThreadUtils() {}

    public static void sleep(long seconds) {
        try {
            Thread.sleep(1000 * seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
