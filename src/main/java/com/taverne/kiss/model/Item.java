package com.taverne.kiss.model;

import lombok.Data;

@Data
public class Item {
    private String name;
    private double price;
    private String type; // "MEAL" ou "DRINK"
}
