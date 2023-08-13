package com.example.demoJPA.Controller;

import com.example.demoJPA.Configuration.AccessDataSource;
import com.example.demoJPA.Configuration.EmailSender;
import com.example.demoJPA.Configuration.SessionCreator;
import com.example.demoJPA.Models.*;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;


@RestController("/api/")
public class homeController {

    @Autowired
    EmailSender sender;

    @Autowired
    AccessDataSource ds;
    @Value("${frontend.path}")
    private String frontendUrl;

    @GetMapping("/hello")
    public String Test(){
        return "Hello";
    }

    @GetMapping("/facts")
    public ResponseEntity<ArrayList<RecyclingFacts>> fax(){
        return new ResponseEntity<>(ds.getRandomRows(10),HttpStatus.OK);
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
    public ResponseEntity<?> email(String addr) throws Exception {
        //get de user
        var u = ds.findUser(addr);
        if(u!=null){
            //create reset email
            var token = SessionCreator.createSession();
            String s = frontendUrl+"recovery.html?tkn="+token;

            sender.SendEmail("password reset",   "hello, "+u.getName()+ " you have forgotten your recycle SA password visit this link to reset your password: "+s,u.getEmail());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/reset")
    public ResponseEntity<?> reset(@RequestBody LoginModel loginModel)  {
        //get de user
        var u = ds.findUser(loginModel.getEmail());
        if(u!=null){
            //create reset email
            if(SessionCreator.checkSession(loginModel.getTkn())){
                try {
                    ds.changePasswordByEmail(loginModel.getEmail(),loginModel.getPassword());
                    sender.SendEmail("password reset",   "hello, "+u.getName()+ "your password was reset on: "+new Date(),u.getEmail());

                    return new ResponseEntity<>(HttpStatus.OK);
                } catch (Exception e) {

                }
            };
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

    @GetMapping("/centers")
    public ResponseEntity<ArrayList<RecyclingPlant>> centers() {

        return new ResponseEntity<>(ds.getRecyclingPlants(),HttpStatus.OK);
    }

    @GetMapping("/faqItems")
    public ResponseEntity<ArrayList<FaqItem>> faqItems() {

        return new ResponseEntity<>(ds.getFaqData(),HttpStatus.OK);
    }

    @GetMapping("/faqSearch")
    public ResponseEntity<String> faqSearch(String s) {

        return new ResponseEntity<>(ds.searchFaqData(s),HttpStatus.OK);
    }

    @GetMapping("/methods")
    public ResponseEntity<ArrayList<Methods>> methods() {

        return new ResponseEntity<>(ds.getMethodData(),HttpStatus.OK);
    }

    @GetMapping("/userData")
    public ResponseEntity<ArrayList<Transaction>> userData(String email) {
        var data = ds.getTransactionData(ds.findUser(email).getId());
        Collections.sort(data);
        return new ResponseEntity<>(data,HttpStatus.OK);
    }

    @GetMapping("/userFootprint")
    public ResponseEntity<String> userFootprint(String email) {

        return new ResponseEntity<>("yellow",HttpStatus.OK);
    }
}
