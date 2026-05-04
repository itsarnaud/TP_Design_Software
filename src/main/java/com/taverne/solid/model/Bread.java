package com.taverne.solid.model;

import com.taverne.solid.interfaces.ICookable;

public class Bread implements ICookable {
    @Override
    public void cook() {
        System.out.println("Baking the bread in the oven...");
    }

    @Override
    public void roast() {
        System.out.println("Toasting the bread slices...");
    }
}
