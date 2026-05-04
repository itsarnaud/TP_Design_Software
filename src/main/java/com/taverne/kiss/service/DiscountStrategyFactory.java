package com.taverne.kiss.service;

public class DiscountStrategyFactory {

    public static ICalculationStrategy createStrategy(
            String discountType, ICalculationStrategy base) {

        switch (discountType) {
            case "ADVENTURER_MENU":
                return new AdventurerMenuDiscountDecorator(base);
            case "NONE":
                return base;
            default:
                throw new IllegalArgumentException("Unknown strategy: " + discountType);
        }
    }
}
