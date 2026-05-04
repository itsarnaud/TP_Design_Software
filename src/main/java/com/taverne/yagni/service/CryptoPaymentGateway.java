package com.taverne.yagni.service;

import org.springframework.stereotype.Service;

@Service
public class CryptoPaymentGateway {

    public boolean processGoblinCoin(Double amount, String currency) {
        // intégrer l'API gobelin de paiement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public String getExchangeRate(String fromCurrency, String toCurrency) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
