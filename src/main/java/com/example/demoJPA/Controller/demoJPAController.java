package com.example.demoJPA.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
public class demoJPAController {

    @GetMapping("hello")
    public String Test(){
        return "Hello";
    }
}
