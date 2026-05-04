package com.taverne.yagni.controller;

import com.taverne.yagni.model.Reservation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final List<Reservation> reservations = new ArrayList<>();

    @PostMapping
    public ResponseEntity<Reservation> create(@Valid @RequestBody Reservation reservation) {
        reservations.add(reservation);
        return ResponseEntity.ok(reservation);
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> list() {
        return ResponseEntity.ok(reservations);
    }
}
