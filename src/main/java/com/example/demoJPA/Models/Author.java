package com.example.demoJPA.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Author {
    @Id
    private UUID Id;
    private String FirstName;
    private String LastName;
    @OneToMany
    private List<Book> Books;
    @Temporal(TemporalType.DATE)
    private Date DateOfBirth;
}
