package com.example.demoJPA.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Transaction implements Comparable<Transaction>{
    private int Quantity;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime date;
    private String material;

    @Override
    public int compareTo(Transaction o) {
        return getDate().compareTo(o.getDate());
    }
}
