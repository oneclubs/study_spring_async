package com.oneclubs.study.async.app;

import com.oneclubs.study.common.ThreadUtils;
import java.util.concurrent.CompletableFuture;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
public class AsyncService {

    private final Logger L = LoggerFactory.getLogger(this.getClass());

    @Async
    public ListenableFuture<String> getListenableValue() {

        ThreadUtils.sleep(3);

        L.info("Return 10 random string name");
        return new AsyncResult<>(RandomStringUtils.randomAlphabetic(10));
    }

    @Async
    public CompletableFuture<String> getCompletableFutureResult() {

        ThreadUtils.sleep(2);

        L.info("Return 5 random string name");
        return CompletableFuture.completedFuture(RandomStringUtils.randomAlphabetic(5));
    }
}
