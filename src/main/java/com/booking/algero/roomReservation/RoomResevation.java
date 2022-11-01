package com.booking.algero.roomReservation;

import com.booking.algero.reservation.Reservation;
import com.booking.algero.room.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "room_resevation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomResevation {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    private LocalDateTime reservationDate;

    public RoomResevation(Room room, Reservation reservation, LocalDateTime reservationDate) {
        this.room = room;
        this.reservation = reservation;
        this.reservationDate = reservationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}