package com.taverne.dry.controller;

import com.taverne.dry.model.PaymentRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @PostMapping("/warrior")
    public ResponseEntity<Double> calculateWarrior(@RequestBody PaymentRequest req) {
        double base      = req.getPrice() * req.getQuantity();
        double kingTax   = base * 0.05;
        double surcharge = 2.0;
        return ResponseEntity.ok(base + kingTax + surcharge);
    }

    @PostMapping("/mage")
    public ResponseEntity<Double> calculateMage(@RequestBody PaymentRequest req) {
        double base = req.getPrice() * req.getQuantity();
        // Les mages contournent le trésor royal
        return ResponseEntity.ok(base);
    }

    @PostMapping("/rogue")
    public ResponseEntity<Double> calculateRogue(@RequestBody PaymentRequest req) {
        double base    = req.getPrice() * req.getQuantity();
        double kingTax = base * 0.05;
        return ResponseEntity.ok(base + kingTax);
    }
}
