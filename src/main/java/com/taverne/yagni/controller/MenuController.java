package com.taverne.yagni.controller;

import com.taverne.yagni.model.MenuItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    private final List<MenuItem> menu = List.of(
            buildItem("Ale Blonde de la Plaine", 3.5, "BEER"),
            buildItem("Bière Brune des Montagnes", 4.0, "BEER"),
            buildItem("Ragoût du Tavernier", 6.0, "FOOD")
    );

    @GetMapping
    public ResponseEntity<List<MenuItem>> listMenu() {
        return ResponseEntity.ok(menu);
    }

    private MenuItem buildItem(String name, double price, String category) {
        MenuItem item = new MenuItem();
        item.setName(name);
        item.setPrice(price);
        item.setCategory(category);
        return item;
    }
}
