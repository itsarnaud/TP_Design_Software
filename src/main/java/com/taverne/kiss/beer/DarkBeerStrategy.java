package com.taverne.kiss.beer;

public class DarkBeerStrategy extends AbstractBeerSelectorStrategy {

    public DarkBeerStrategy(IBeerSelector next) {
        super(next);
    }

    @Override
    public String selectBeer(BeerPreference preference) {
        return "Brune";
    }
}
