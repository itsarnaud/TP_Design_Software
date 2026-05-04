package com.taverne.yagni.controller;

import com.taverne.yagni.model.Reservation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final List<Reservation> reservations = new ArrayList<>();

    @PostMapping
    public ResponseEntity<Reservation> create(@RequestBody Reservation reservation) {
        reservations.add(reservation);
        return ResponseEntity.ok(reservation);
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> list() {
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/vip")
    public ResponseEntity<List<Reservation>> listVip() {
        // filtrer par vipTier
        return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping("/weather-check")
    public ResponseEntity<String> checkWeather(@RequestParam String date) {
        // appeler WeatherIntegrationService
        return ResponseEntity.ok("unknown");
    }
}
