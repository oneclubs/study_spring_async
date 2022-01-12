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
import org.springframework.web.context.request.async.DeferredResult;

@Service
public class AsyncService {

    private final Logger L = LoggerFactory.getLogger(this.getClass());

    @Async
    public ListenableFuture<String> getListenableValue() {

        L.info("Sleep 3 seconds");
        ThreadUtils.sleep(3);

        L.info("Return 10 random string name");
        return new AsyncResult<>(RandomStringUtils.randomAlphabetic(10));
    }

    @Async
    public CompletableFuture<String> getCompletableFutureResult() {

        L.info("Sleep 2 seconds");
        ThreadUtils.sleep(2);

        L.info("Return 5 random string name");
        return CompletableFuture.completedFuture(RandomStringUtils.randomAlphabetic(5));
    }

    @Async
    public void setDeferredResult(DeferredResult<String> result) {

        L.info("Sleep 4 seconds");
        ThreadUtils.sleep(4);

        L.info("set result to deferred");
        result.setResult("deferred name");
    }
}
