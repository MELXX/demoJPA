package com.example.demoJPA.Controller;

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

}
