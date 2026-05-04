package com.taverne.kiss.controller;

import com.taverne.kiss.model.CalculationResult;
import com.taverne.kiss.model.OrderRequest;
import com.taverne.kiss.service.*;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tab")
public class TabController {

    private final OrderCalculatorService calculatorService;

    public TabController(OrderCalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<CalculationResult> calculate(@RequestBody OrderRequest request) {

        boolean hasMeal  = request.getItems().stream()
                .anyMatch(i -> "MEAL".equals(i.getType()));
        boolean hasDrink = request.getItems().stream()
                .anyMatch(i -> "DRINK".equals(i.getType()));

        DiscountType discount = (hasMeal && hasDrink) ? DiscountType.ADVENTURER : DiscountType.NONE;

        String strategyType = (hasMeal && hasDrink) ? "ADVENTURER_MENU" : "NONE";

        return ResponseEntity.ok(calculatorService.calculate(request, discount));
    }
}
