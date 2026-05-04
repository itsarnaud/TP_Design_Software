package com.taverne.kiss.controller;

import com.taverne.kiss.model.CalculationResult;
import com.taverne.kiss.model.OrderRequest;
import com.taverne.kiss.service.BaseOrderCalculator;
import com.taverne.kiss.service.DiscountStrategyFactory;
import com.taverne.kiss.service.ICalculationStrategy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tab")
public class TabController {

    @PostMapping("/calculate")
    public ResponseEntity<CalculationResult> calculate(@RequestBody OrderRequest request) {

        boolean hasMeal  = request.getItems().stream()
                .anyMatch(i -> "MEAL".equals(i.getType()));
        boolean hasDrink = request.getItems().stream()
                .anyMatch(i -> "DRINK".equals(i.getType()));

        String strategyType = (hasMeal && hasDrink) ? "ADVENTURER_MENU" : "NONE";

        ICalculationStrategy strategy = DiscountStrategyFactory.createStrategy(
                strategyType, new BaseOrderCalculator());

        return ResponseEntity.ok(strategy.calculate(request));
    }
}
