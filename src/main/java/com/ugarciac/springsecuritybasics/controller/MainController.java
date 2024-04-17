package com.ugarciac.springsecuritybasics.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class MainController {

    @GetMapping("/sayHello")
    public String sayHello() {
        return "Hello World";
    }
}
