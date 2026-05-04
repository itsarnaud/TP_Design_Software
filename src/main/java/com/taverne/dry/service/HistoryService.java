package com.taverne.dry.service;

import com.taverne.dry.model.PaymentRequest;
import org.springframework.stereotype.Service;

@Service
public class HistoryService {

    public String generateSummary(PaymentRequest req, String guild) {
        double base    = req.getPrice() * req.getQuantity();
        double kingTax = base * 0.05;

        double total = "WARRIOR".equals(guild)
                ? base + kingTax + 2.0
                : "MAGE".equals(guild)
                    ? base
                    : base + kingTax;

        return guild + " — Total : " + total + " po";
    }
}
