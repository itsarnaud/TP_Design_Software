package com.taverne.kiss.service;

public enum DiscountType {
    NONE(1.0),
    ADVENTURER(0.90);

    private final double multiplier;

    DiscountType(double multiplier) {
        this.multiplier = multiplier;
    }

    public double applyDiscount(double amount) {
        return amount * this.multiplier;
    }
}
