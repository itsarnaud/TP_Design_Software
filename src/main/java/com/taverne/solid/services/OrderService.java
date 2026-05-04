package com.taverne.solid.services;

import com.taverne.solid.model.OrderRequest;
import com.taverne.solid.repository.INotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final List<OrderRequest> orders = new ArrayList<>();

    private final INotificationRepository notificationRepository;

    public OrderService(INotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void registerOrder(OrderRequest request) {
        notificationRepository.save("Order: " + request.getItem() + " x" + request.getQty());

        System.out.println("[" + LocalDateTime.now() + "] Order processed: " + request.getItem());
        orders.add(request);
    }
}
