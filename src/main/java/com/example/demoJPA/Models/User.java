package com.example.demoJPA.Models;

import lombok.Data;

import java.util.UUID;

@Data
public class User {
    private UUID Id;
    private String Name;
    private String Email;
    private String PasswordHash;
}
