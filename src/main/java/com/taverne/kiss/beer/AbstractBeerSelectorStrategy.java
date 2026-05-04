package com.taverne.kiss.beer;

public abstract class AbstractBeerSelectorStrategy implements IBeerSelector {

    protected IBeerSelector next;

    public AbstractBeerSelectorStrategy(IBeerSelector next) {
        this.next = next;
    }
}
