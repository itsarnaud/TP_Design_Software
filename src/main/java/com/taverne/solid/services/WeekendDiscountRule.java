package com.taverne.solid.services;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekendDiscountRule implements IPricingRule {
    public double apply(double baseTotal) {
        if (LocalDate.now().getDayOfWeek() == DayOfWeek.SATURDAY) {
            return -baseTotal * 0.05;
        }
        return 0;
    }
}
