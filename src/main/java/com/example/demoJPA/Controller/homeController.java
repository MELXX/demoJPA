package com.example.demoJPA.Controller;

import com.example.demoJPA.Configuration.AccessDataSource;
import com.example.demoJPA.Configuration.EmailSender;
import com.example.demoJPA.Configuration.SessionCreator;
import com.example.demoJPA.Models.LoginModel;
import com.example.demoJPA.Models.User;
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
import java.util.Date;


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

    @GetMapping("/recoverEmail")
    public String email(String addr) throws Exception {
        //get de user
        var u = ds.findUser(addr);
        if(u!=null){
            //create reset email
            var token = SessionCreator.createSession();
            String s = "http://localhost:8080/api/reset?tkn="+token;

            sender.SendEmail("password reset",   "hello, "+u.getName()+ " you have forgotten your recycle SA password visit this link to reset your password: "+s,u.getEmail());
        }
        return "";
    }

    @GetMapping("/reset")
    public ResponseEntity<?> reset(String tkn,String addr,String newPassword)  {
        //get de user
        var u = ds.findUser(addr);
        if(u!=null){
            //create reset email
            if(SessionCreator.checkSession(tkn)){
                try {
                    ds.changePasswordByEmail(addr,newPassword);
                    sender.SendEmail("password reset",   "hello, "+u.getName()+ "your password was reset on: "+new Date(),u.getEmail());

                    return new ResponseEntity<>(HttpStatus.OK);
                } catch (Exception e) {

                }
            };
           // String s = "http://localhost:8080/api/reset?token="+token;

           // sender.SendEmail("password reset",   "hello, "+u.getName()+ " you have forgotten your recycle SA password visit this link to reset your password: "+s,u.getEmail());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/Login")
    public ResponseEntity<?> email(@RequestBody LoginModel model) throws Exception {

        boolean result = ds.Login(model.getPassword(), model.getEmail());
        var tkn = SessionCreator.createSession();
        System.out.println(tkn);
        return new ResponseEntity<>(result?  tkn:"", result?HttpStatus.OK:HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validate(@RequestBody token token) throws IOException {

        boolean result = SessionCreator.checkSession(token.token);
        return new ResponseEntity<>(result?HttpStatus.BAD_REQUEST:HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        if(ds.findUser(user.getEmail()) != null){
            return new ResponseEntity<>("user already exists",HttpStatus.CONFLICT);
        }
        ds.insertUserRow(user.getName(), user.getName(),
                user.getEmail(), user.getPhonenumber(), user.getDob(), user.getPasswordHash());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
