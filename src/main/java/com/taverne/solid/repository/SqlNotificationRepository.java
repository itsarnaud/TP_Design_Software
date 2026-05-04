package com.taverne.solid.repository;

public class SqlNotificationRepository {

    public void save(String message) {
        System.out.println("[SQL] Saving notification: " + message);
    }
}
