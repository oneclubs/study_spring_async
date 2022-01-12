package com.oneclubs.study.async.controller;

import com.oneclubs.study.async.app.AsyncService;
import com.oneclubs.study.common.ThreadUtils;
import java.util.concurrent.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

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
}
