package com.taverne.solid.model;

public class PoisonousDrink extends ConsumableItem {

    @Override
    public boolean isSafeToConsume() {
        throw new RuntimeException("Hazardous! Do not consume!");
    }
}
