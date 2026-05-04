package com.taverne.dry.model;

import lombok.Data;

@Data
public class PaymentRequest {
    private double price;
    private int    quantity;
}
