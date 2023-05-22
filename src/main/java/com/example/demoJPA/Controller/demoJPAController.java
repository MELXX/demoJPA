package com.example.demoJPA.Controller;

import com.example.demoJPA.Models.Publisher;
import com.example.demoJPA.Repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/")
public class demoJPAController {

    @Autowired
    PublisherRepository publisherRepository;

    @GetMapping("/hello")
    public String Test(){
        publisherRepository.save(new Publisher());
        return "Hello";
    }
}
