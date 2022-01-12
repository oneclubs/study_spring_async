package com.oneclubs.study.async.controller;

import com.oneclubs.study.async.app.AsyncService;
import java.util.concurrent.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsyncController {

    private final Logger L = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AsyncService service;

    @GetMapping("/name-callable")
    Callable<String> getNameCallable() {
        L.info("name callable");
        return () -> {
            L.info("return callbale");
            return "callable";
        };
    }
}
