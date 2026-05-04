package com.taverne.kiss.service;

import com.taverne.kiss.model.CalculationResult;
import com.taverne.kiss.model.OrderRequest;

public class AdventurerMenuDiscountDecorator extends AbstractOrderCalculatorDecorator {

    public AdventurerMenuDiscountDecorator(ICalculationStrategy strategy) {
        super(strategy);
    }

    @Override
    public CalculationResult calculate(OrderRequest request) {
        CalculationResult base = wrapped.calculate(request);
        return new CalculationResult(base.getAmount() * 0.90);
    }
}
