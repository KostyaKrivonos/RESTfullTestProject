/*
package com.example.loguemo.controllers;

import com.example.loguemo.models.Greeting;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class HelloWorldController {


    private static final Logger LOGGER = LogManager.getLogger(HelloWorldController.class);

    private static final String template = "Hello, %s!";

    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/hello-world")
    @ResponseBody
    public Greeting sayHello(@RequestParam(name="name", required=false, defaultValue="Stranger") String name) {
        LOGGER.info("info");
        LOGGER.warn("warn");
        LOGGER.error("error");
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

}
*/
