package com.taverne.kiss.service;

import com.taverne.kiss.model.CalculationResult;
import com.taverne.kiss.model.OrderRequest;
import org.springframework.stereotype.Service;

@Service
public class OrderCalculatorService {
    public CalculationResult calculate(OrderRequest request, DiscountType discountType) {
        double total = request.getItems()
                .stream()
                .mapToDouble(i -> i.getPrice())
                .sum();

        double finalAmount = discountType.applyDiscount(total);
        return new CalculationResult(finalAmount);
    }
}
