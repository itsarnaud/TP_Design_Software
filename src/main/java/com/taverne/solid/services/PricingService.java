package com.taverne.solid.services;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PricingService {

    private final List<IPricingRule> rules;

    public PricingService(List<IPricingRule> rules) {
        this.rules = rules;
    }

    public double calculateTotalAmount(double price, int qty) {
        double baseTotal = price * qty;
        double modifiers = 0;

        for (IPricingRule rule : rules) {
            modifiers += rule.apply(baseTotal);
        }

        return baseTotal + modifiers;
    }
}
