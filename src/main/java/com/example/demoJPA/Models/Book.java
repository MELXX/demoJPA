package com.example.demoJPA.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Book {
    @Id
    private UUID Id;
    private String Title;
    private String ISBN;
    @Enumerated(EnumType.STRING)
    private Genre Genre;
    private Author Author;
    private Publisher Publisher;
}
