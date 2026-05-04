package com.taverne.yagni.service;

public interface INotificationService {
    void sendPalantirAlert(String reservation);
    void sendSmokeSignal(String groupName);
    void notifyKitchen(Long reservationId);
    void sendCryptoReceipt(Double amount, String currency);
}
