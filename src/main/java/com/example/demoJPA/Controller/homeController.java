package com.example.demoJPA.Controller;

import com.example.demoJPA.Configuration.EmailSender;
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
    EmailSender sender;

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

}
