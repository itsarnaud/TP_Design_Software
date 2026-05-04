package com.taverne.solid.repository;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryNotificationRepository implements INotificationRepository {

    @Override
    public void save(String message) {
        System.out.println("[IN-MEMORY] Saving notification: " + message);
    }
}
