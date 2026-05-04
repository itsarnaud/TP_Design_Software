package com.taverne.kiss.beer;

public class BeerSelectorFactory {

    public static IBeerSelector build() {
        return new LightBeerStrategy(new DarkBeerStrategy(null));
    }
}
