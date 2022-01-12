package com.oneclubs.study.async.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class AsyncExecutionService {

    private final Logger L = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AsyncExecutionJob job;

    @EventListener(ApplicationReadyEvent.class)
    public void onReady() {

        L.info("-----------------------------------");
        callListenableFuture();
        callCompletableFuture();
        L.info("-----------------------------------");
    }

    private void callListenableFuture() {

        L.info("Call Async ListenableFuture Function");

        job.getListenableFutureResult()
            .addCallback(
                value -> L.info("Random User Name : {}", value),
                ex -> {}
            );
    }

    private void callCompletableFuture() {

        L.info("Call Async CompletableFuture Function");

        job.getCompletableFutureResult()
            .thenAccept(value -> L.info("Random User Name : {}", value));
    }


}
