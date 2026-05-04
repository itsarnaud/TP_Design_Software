package com.taverne.solid.model;

import com.taverne.solid.interfaces.IPourrable;

public class Ale implements IPourrable {
    @Override
    public void pourIntoMug() {
        System.out.println("Pouring the ale into a large wooden mug...");
    }
}
