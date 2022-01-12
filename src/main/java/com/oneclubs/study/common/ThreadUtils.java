package com.oneclubs.study.common;

public final class ThreadUtils {

    ThreadUtils() {}

    public static void sleep(double seconds) {
        try {
            Thread.sleep((long)(1000 * seconds));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
