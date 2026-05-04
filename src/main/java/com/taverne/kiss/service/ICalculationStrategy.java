package com.taverne.kiss.service;

import com.taverne.kiss.model.CalculationResult;
import com.taverne.kiss.model.OrderRequest;

public interface ICalculationStrategy {
    CalculationResult calculate(OrderRequest request);
}
