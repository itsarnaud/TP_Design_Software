package com.taverne.yagni.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Reservation {

    private Long id;

    @NotBlank(message = "Le nom du groupe est obligatoire.")
    private String groupName;

    private LocalDateTime arrivalTime;

    @Min(value = 1, message = "Il doit y avoir au moins 1 personne.")
    @Max(value = 10, message = "Le groupe ne peut pas dépasser 10 personnes.")
    private int guestCount;
}
