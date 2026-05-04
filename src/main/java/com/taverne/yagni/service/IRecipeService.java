package com.taverne.yagni.service;

import java.util.List;

public interface IRecipeService {
    List<String> getIngredients(String beerName);
    String getBrewingInstructions(String beerName);
    boolean isPairableWith(String beerName, String foodName);
}
