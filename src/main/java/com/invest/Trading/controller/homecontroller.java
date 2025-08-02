package com.invest.Trading.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class homecontroller {

    @GetMapping
    public String home(){
        return "Welcome to home controller";
    }

    @GetMapping("/api")
    public String secure(){ return "Welcome to secured";}

}
