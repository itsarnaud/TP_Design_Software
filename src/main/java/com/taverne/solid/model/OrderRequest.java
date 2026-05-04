package com.taverne.solid.model;

import lombok.Data;

@Data
public class OrderRequest {
    private String item;
    private int    qty;
    private double price;
}
