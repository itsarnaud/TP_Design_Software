package com.taverne.solid.services;

public interface IPricingRule {
    double apply(double baseTotal);
}
