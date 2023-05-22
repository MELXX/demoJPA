package com.example.demoJPA.Controller;

import com.example.demoJPA.Models.Publisher;
import com.example.demoJPA.Repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController("/api/")
public class demoJPAController {

    @Autowired
    PublisherRepository publisherRepository;

    @GetMapping("/hello")
    public String Test(){
        publisherRepository.save(new Publisher());
        return "Hello";
    }

    @GetMapping("/helloMany")
    public String TestMany(){
        List<Publisher> result = new ArrayList<Publisher>();
        for (int i = 0; i < 10; i++) {
            result.add(new Publisher(UUID.randomUUID().toString()));
        }
        publisherRepository.saveAll(result);
        return "Hello";
    }

    @GetMapping("/publisherCount")
    public long TestCount(){
        return publisherRepository.count();
    }
}
