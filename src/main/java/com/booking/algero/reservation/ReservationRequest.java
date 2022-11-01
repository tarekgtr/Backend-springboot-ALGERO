package com.booking.algero.reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor @AllArgsConstructor
public class ReservationRequest {
    private Long userId;
    private Long hotelId;
    private int nbChambre;
    private LocalDate startDate;
    private LocalDate endDate;
    private String type;


}
