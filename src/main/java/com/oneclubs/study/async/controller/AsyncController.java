package com.oneclubs.study.async.controller;

import com.oneclubs.study.async.app.AsyncService;
import com.oneclubs.study.common.ThreadUtils;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class AsyncController {

    private final Logger L = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AsyncService service;

    @GetMapping("/name-callable")
    Callable<String> getNameCallable() {
        L.info("name callable");
        return () -> {
            L.info("return callable");
            return "callable";
        };
    }

    @GetMapping("/name-web-async")
    WebAsyncTask<String> getNameWebAsync() {
        L.info("name web async");
        return new WebAsyncTask<>(
            1000,
            () -> {
                ThreadUtils.sleep(2);
                return "web async maybe timeout";
            }
        );
    }

    @GetMapping("/name-defer")
    DeferredResult<String> getNameDefer() {
        L.info("name deferred");
        DeferredResult<String> result = new DeferredResult<>();
        service.setDeferredResult(result);
        return result;
    }

    @GetMapping("/name-listenable")
    ListenableFuture<String> getNameListenable() {
        L.info("name listenable");
        return service.getListenableValue();
    }

    @GetMapping("/name-completable")
    CompletableFuture<String> getNameCompletable() {
        L.info("name completable");
        return service.getCompletableFutureResult()
            .thenCompose(value -> service.appendCompletableFutureResult(value));
    }

    @GetMapping("/name-emitter")
    ResponseBodyEmitter getNameEmitter() {
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        service.appendNameToEmitter(emitter);
        return emitter;
    }

    @GetMapping("/name-sse-emitter")
    SseEmitter getNameSseEmitter() {
        SseEmitter emitter = new SseEmitter();
        service.appendNameToEmitter(emitter);
        return emitter;
    }
}
