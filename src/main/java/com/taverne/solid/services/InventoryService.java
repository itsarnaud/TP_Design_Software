package com.taverne.solid.services;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InventoryService {
    private final Map<String, Integer> stock = new HashMap<>() {{
        put("ALE",      100);
        put("BREAD",    50);
        put("STEW",     30);
    }};

    public boolean isStockAvailable(String item, int qty) {
        return stock.containsKey(item) && stock.get(item) >= qty;
    }

    public void decrementStock(String item, int qty) {
        stock.put(item, stock.get(item) - qty);
    }
}
