package com.booking.algero.reservation;

import com.booking.algero.shared.ResponseHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "${v1Api}/reservation")
@AllArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/add")
    public ResponseEntity<Object> reserver(@RequestBody ReservationRequest reservationRequest){
        try {
            return ResponseHandler.response("reservation done",
                    HttpStatus.ACCEPTED,
                    reservationService.newReservation(reservationRequest));
        }catch (Exception e){
            return ResponseHandler.response(e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    null);
        }

    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Object> deleteReservation(@PathVariable("id") Long id){
        try {






            return ResponseHandler.response("reservation done",
                    HttpStatus.ACCEPTED,
                    "");
        }catch (Exception e){
            return ResponseHandler.response(e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    null);
        }

    }

}
