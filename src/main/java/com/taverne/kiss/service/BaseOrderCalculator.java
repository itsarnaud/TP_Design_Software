package com.taverne.kiss.service;

import com.taverne.kiss.model.CalculationResult;
import com.taverne.kiss.model.OrderRequest;

public class BaseOrderCalculator implements ICalculationStrategy {

    @Override
    public CalculationResult calculate(OrderRequest request) {
        double total = request.getItems()
                .stream()
                .mapToDouble(i -> i.getPrice())
                .sum();
        return new CalculationResult(total);
    }
}
