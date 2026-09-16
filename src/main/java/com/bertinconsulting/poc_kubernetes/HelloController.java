package com.bertinconsulting.poc_kubernetes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        throw new RuntimeException("Version cassée !");
    }

    @GetMapping("/version")
    public String version() {
        return "Version 4.1";
    }
}
