package com.taverne.solid.controller;

import com.taverne.solid.model.ConsumableItem;
import com.taverne.solid.model.OrderRequest;
import com.taverne.solid.model.PoisonousDrink;
import com.taverne.solid.repository.SqlNotificationRepository;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TavernManager {

    private final List<OrderRequest> orders = new ArrayList<>();
    private final Map<String, Integer> stock = new HashMap<>() {{
        put("ALE",   100);
        put("BREAD",  50);
        put("STEW",   30);
    }};

    @PostMapping("/api/order")
    public String processOrder(@RequestBody OrderRequest request) {

        String item = request.getItem();
        int    qty  = request.getQty();

        if (!stock.containsKey(item) || stock.get(item) < qty) {
            return "Stock insuffisant";
        }
        stock.put(item, stock.get(item) - qty);

        double total = request.getPrice() * qty;
        double tax   = total * 0.05;

        if (LocalTime.now().getHour() >= 22) {
            tax += total * 0.10;
        }
        if (LocalDate.now().getDayOfWeek() == DayOfWeek.SATURDAY) {
            tax -= total * 0.05;
        }

        SqlNotificationRepository repo = new SqlNotificationRepository();
        repo.save("Order: " + item + " x" + qty);

        System.out.println("[" + LocalDateTime.now() + "] Order processed: " + item);

        orders.add(request);
        return "Total = " + (total + tax) + " po";
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
