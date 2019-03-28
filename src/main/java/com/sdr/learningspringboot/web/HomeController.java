package com.sdr.learningspringboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String qreeting(@RequestParam(required = false, defaultValue = "") String name) {
        return "Hey " + name + "!";
    }

}
