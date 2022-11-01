package com.booking.algero.roomReservation;

import com.booking.algero.roomReservation.RoomResevation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomResevationRepository extends JpaRepository<RoomResevation, Long> {
}