package com.oneclubs.study.async.app;

import com.oneclubs.study.common.ThreadUtils;
import java.util.concurrent.CompletableFuture;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
public class AsyncExecutionJob {

    private final Logger L = LoggerFactory.getLogger(this.getClass());

    @Async
    public ListenableFuture<String> getListenableFutureResult() {

        L.info("ListenableFuture Sleep 5 seconds");
        ThreadUtils.sleep(5);

        L.info("Return 10 random string name");

        return new AsyncResult<>(RandomStringUtils.randomAlphabetic(10));
    }

    @Async
    public CompletableFuture<String> getCompletableFutureResult() {

        L.info("CompletableFuture Sleep 2 seconds");
        ThreadUtils.sleep(2);

        L.info("Return 5 random string name");

//        return new AsyncResult<>(RandomStringUtils.randomAlphabetic(5)).completable();
        return CompletableFuture.completedFuture(RandomStringUtils.randomAlphabetic(5));
    }
}
