package com.ugarciac.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoansController {

    @GetMapping("/myLoans")
    private String myLoans() {
        return "Hello from /myLoans Controller";
    }
}
