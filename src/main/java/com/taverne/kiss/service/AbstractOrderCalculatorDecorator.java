package com.taverne.kiss.service;

import com.taverne.kiss.model.CalculationResult;
import com.taverne.kiss.model.OrderRequest;

public abstract class AbstractOrderCalculatorDecorator implements ICalculationStrategy {

    protected ICalculationStrategy wrapped;

    public AbstractOrderCalculatorDecorator(ICalculationStrategy strategy) {
        this.wrapped = strategy;
    }

    @Override
    public abstract CalculationResult calculate(OrderRequest request);
}
