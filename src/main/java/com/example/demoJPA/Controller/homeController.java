package com.example.demoJPA.Controller;

import com.example.demoJPA.Repositories.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.healthmarketscience.jackcess.*;

import java.io.File;
import java.io.IOException;


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

    @SneakyThrows
    @GetMapping("/testAccess")
    public String Access() throws IOException {
        Database db = DatabaseBuilder.open(new File("mydb.mdb"));
        return "";
    }

}
