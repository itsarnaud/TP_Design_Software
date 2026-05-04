package com.taverne.solid.services;

import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class NightSurchargeRule implements IPricingRule {
    @Override
    public double apply(double baseTotal) {
        if (LocalTime.now().getHour() >= 22) {
            return baseTotal * 0.10;
        }
        return 0;
    }
}
