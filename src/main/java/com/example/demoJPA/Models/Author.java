package com.example.demoJPA.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
    private List<Book> Books;
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
}
