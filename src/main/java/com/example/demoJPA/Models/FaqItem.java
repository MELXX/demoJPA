package com.example.demoJPA.Models;

import lombok.Data;

import java.util.UUID;

@Data
public class FaqItem {
    private UUID Id;
    private String Topic;
    private String Description;
}
