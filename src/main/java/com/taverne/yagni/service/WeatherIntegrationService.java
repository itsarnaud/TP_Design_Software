package com.taverne.yagni.service;

import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class WeatherIntegrationService {

    public String getWeatherForDate(LocalDate date) {
        // appeler l'API météo du royaume
        return null;
    }

    public boolean isTerraceFriendly(String weather) {
        return false;
    }
}
