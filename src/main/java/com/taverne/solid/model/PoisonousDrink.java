package com.taverne.solid.model;

public class PoisonousDrink extends ConsumableItem {

    @Override
    public boolean isSafeToConsume() {
        return false;
    }
}
