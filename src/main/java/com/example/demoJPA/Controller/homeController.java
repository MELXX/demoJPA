package com.example.demoJPA.Controller;

import com.example.demoJPA.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/")
public class homeController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/hello")
    public String Test(){
        return "Hello";
    }

    @PostMapping("/Login")
    public String Login(String password,String email){
        ;
        return userRepository.Login(email,password)?"Can log in":"nahh bruv";
    }

}
