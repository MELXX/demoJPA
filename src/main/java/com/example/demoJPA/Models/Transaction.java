package com.example.demoJPA.Models;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Transaction {
    private int Quantity;
    private LocalDateTime date;
    private String material;
}
