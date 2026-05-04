package com.taverne.kiss.beer;

public class LightBeerStrategy extends AbstractBeerSelectorStrategy {

    public LightBeerStrategy(IBeerSelector next) {
        super(next);
    }

    @Override
    public String selectBeer(BeerPreference preference) {
        if (preference.isLight()) return "Blonde";
        return next.selectBeer(preference);
    }
}
