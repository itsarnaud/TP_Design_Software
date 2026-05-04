package com.taverne.solid.model;

public class ConsumableItem {

    protected String name;

    public boolean isSafeToConsume() {
        return true;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
