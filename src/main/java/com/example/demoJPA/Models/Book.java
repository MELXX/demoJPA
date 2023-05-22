package com.example.demoJPA.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.UUID;

@Data
@Entity
public class Book {
    @Id
    private UUID Id;
    private String Title;
    @Temporal(TemporalType.DATE)
    private Date YearPublished;
    private String ISBN;
    @Enumerated(EnumType.STRING)
    private Genre Genre;
    @ManyToOne
    private Author Author;
    @ManyToOne
    private Publisher Publisher;
}
