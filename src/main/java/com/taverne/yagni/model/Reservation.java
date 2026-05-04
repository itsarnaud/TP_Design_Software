package com.taverne.yagni.model;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Reservation {

    private Long          id;
    private String        groupName;
    private LocalDateTime arrivalTime;
    private int           guestCount;
    private String        cryptoCurrency;
    private String        weatherForecast;
    private boolean       isPaid;
    private String        palantirChannelId;
    private String        vipTier;
    private List<String>  dietaryRestrictions;
    private Double        discountCodePct;
    private String        referralSource;
}
