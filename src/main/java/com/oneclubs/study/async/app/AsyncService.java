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
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

@Service
public class AsyncService {

    private final Logger L = LoggerFactory.getLogger(this.getClass());

    @Async
    public ListenableFuture<String> getListenableValue() {

        L.info("Sleep 3 seconds");
        ThreadUtils.sleep(3);

        return new AsyncResult<>("listenable name");
    }

    @Async
    public CompletableFuture<String> getCompletableFutureResult() {

        L.info("Sleep 2 seconds");
        ThreadUtils.sleep(2);

        return CompletableFuture.completedFuture("completable name");
    }

    @Async
    public CompletableFuture<String> appendCompletableFutureResult(String value) {

        L.info("Sleep 1 seconds");
        ThreadUtils.sleep(2);

        return CompletableFuture.completedFuture(value + " + " + " with extra");
    }

    @Async
    public void setDeferredResult(DeferredResult<String> result) {

        L.info("Sleep 4 seconds");
        ThreadUtils.sleep(4);

        L.info("set result to deferred");
        result.setResult("deferred name");
    }

    @Async
    public void appendNameToEmitter(ResponseBodyEmitter emitter) {

        try {
            for (int i = 0; i < 10; i++) {
                L.info("Sleep 0.5 seconds");
                ThreadUtils.sleep(0.5);

                emitter.send("emitter name (" + i + ")\n");
            }

            emitter.complete();
            L.info("Emitter done");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
