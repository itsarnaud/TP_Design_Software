package com.taverne.dry.controller;

import com.taverne.dry.model.PaymentRequest;
import com.taverne.dry.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/warrior")
    public ResponseEntity<Double> calculateWarrior(@RequestBody PaymentRequest req) {
        return ResponseEntity.ok(paymentService.calculateWarrior(req));
    }

    @PostMapping("/mage")
    public ResponseEntity<Double> calculateMage(@RequestBody PaymentRequest req) {
        return ResponseEntity.ok(paymentService.calculateMage(req));
    }

    @PostMapping("/rogue")
    public ResponseEntity<Double> calculateRogue(@RequestBody PaymentRequest req) {
        return ResponseEntity.ok(paymentService.calculateRogue(req));
    }
}
