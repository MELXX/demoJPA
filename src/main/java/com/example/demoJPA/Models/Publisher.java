package com.example.demoJPA.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Publisher {
    @Id
    private UUID Id;
    private String Name;
    private List<Book> Books;
}
