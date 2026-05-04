package com.taverne.solid.controller;

import com.taverne.solid.model.ConsumableItem;
import com.taverne.solid.model.OrderRequest;
import com.taverne.solid.model.PoisonousDrink;
import com.taverne.solid.services.InventoryService;
import com.taverne.solid.services.OrderService;
import com.taverne.solid.services.PricingService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TavernManager {

    private final InventoryService inventoryService;
    private final PricingService pricingService;
    private final OrderService orderService;

    public TavernManager(InventoryService inventoryService, PricingService pricingService, OrderService orderService) {
        this.inventoryService = inventoryService;
        this.pricingService = pricingService;
        this.orderService = orderService;
    }

    @PostMapping("/api/order")
    public String processOrder(@RequestBody OrderRequest request) {

        String item = request.getItem();
        int    qty  = request.getQty();

        if (!inventoryService.isStockAvailable(item, qty)) {
            return "Stock insuffisant";
        }

        inventoryService.decrementStock(item, qty);

        double finalPrice = pricingService.calculateTotalAmount(request.getPrice(), qty);

        orderService.registerOrder(request);

        return "Total = " + finalPrice + " po";
    }

    @GetMapping("/api/consume-check")
    public String consumeCheck() {
        List<ConsumableItem> menu = new ArrayList<>();
        menu.add(new ConsumableItem());
        menu.add(new PoisonousDrink());

        StringBuilder result = new StringBuilder();
        for (ConsumableItem it : menu) {
            result.append(it.isSafeToConsume() ? "safe" : "danger").append("\n");
        }
        return result.toString();
    }
}
