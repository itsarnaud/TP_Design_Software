package com.taverne.dry.service;

import com.taverne.dry.model.PaymentRequest;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private static final double KING_TAX_RATE = 0.05;

    public double calculateWarrior(PaymentRequest req) {
        return calculateBase(req) + calculateKingTax(req) + 2.0;
    }

    public double calculateMage(PaymentRequest req) {
        return calculateBase(req);
    }

    public double calculateRogue(PaymentRequest req) {
        return calculateBase(req) + calculateKingTax(req);
    }

    private double calculateBase(PaymentRequest req) {
        return req.getPrice() * req.getQuantity();
    }

    private double calculateKingTax(PaymentRequest req) {
        return calculateBase(req) * KING_TAX_RATE;
    }
}
