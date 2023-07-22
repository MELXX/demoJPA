package com.example.demoJPA.Models;

import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
public class User {
    @jakarta.persistence.Id
    private UUID Id;
    private String Name;
    private String Email;
    private String PasswordHash;
}
