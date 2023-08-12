package com.example.demoJPA.Controller;

import com.example.demoJPA.Configuration.AccessDataSource;
import com.example.demoJPA.Configuration.EmailSender;
import com.example.demoJPA.Configuration.SessionCreator;
import com.example.demoJPA.Models.token;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.healthmarketscience.jackcess.*;

import java.io.Console;
import java.io.File;
import java.io.IOException;


@RestController("/api/")
public class homeController {

    @Autowired
    EmailSender sender;

    @Autowired
    AccessDataSource ds;

    @GetMapping("/hello")
    public String Test(){
        return "Hello";
    }



    @SneakyThrows
    @GetMapping("/testAccess")
    public String Access() throws IOException {
        Table db = DatabaseBuilder.open(new File("C:\\Users\\melusi\\Downloads\\RecycleSA.accdb"))
                .getTable("tblMaterials");
        for(Row row : db) {
            System.out.println("Column 'a' has value: " + row.get("Type"));
        }
        return "";
    }

    @GetMapping("/testEmail")
    public String email(String addr) throws IOException {
        sender.SendEmail("password reset","you have forgotten your recycle SA password",addr);
        return "";
    }

    @GetMapping("/Login")
    public ResponseEntity<?> email(String password, String email) throws Exception {

        boolean result = ds.Login(password,email);
        var tkn = SessionCreator.createSession();
        System.out.println(tkn);
        return new ResponseEntity<>(result?  tkn:"", result?HttpStatus.OK:HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validate(@RequestBody token token) throws IOException {

        boolean result = SessionCreator.checkSession(token.token);
        return new ResponseEntity<>(result?HttpStatus.BAD_REQUEST:HttpStatus.OK);
    }
}
