package com.example.demoJPA.Models;

import lombok.Data;

import java.util.UUID;

@Data
public class RecyclingPlant {
    private UUID Id;
    private String Name;
    private String Province;
    private String City;
    private String Suburb;
    private String Street;
    private String PostalCode;
    private String Description;
    private String OperatingHours;
}
