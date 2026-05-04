package com.taverne.solid.services;

import org.springframework.stereotype.Component;

@Component
public class KingTaxRule implements IPricingRule {
    @Override
    public double apply(double baseTotal) {
        return baseTotal * 0.05;
    }
}
