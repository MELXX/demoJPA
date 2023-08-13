package com.example.demoJPA.Models;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class User {
    private String Id;
    private String Name;
    private String Surname;
    private String Phonenumber;
    private Date dob;
    private String Email;
    private String PasswordHash;
}
