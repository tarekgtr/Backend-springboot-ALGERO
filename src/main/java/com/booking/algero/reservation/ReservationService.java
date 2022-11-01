package com.booking.algero.reservation;

import com.booking.algero.appUser.AppUser;
import com.booking.algero.appUser.AppUserRepository;
import com.booking.algero.roomCategory.RoomType;
import com.booking.algero.hotel.Hotel;
import com.booking.algero.hotel.HotelRepository;
import com.booking.algero.room.Room;
import com.booking.algero.room.RoomRepository;
import com.booking.algero.roomReservation.RoomResevation;
import com.booking.algero.roomReservation.RoomResevationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ReservationService {

    private final HotelRepository hotelRepository;
    private final AppUserRepository appUserRepository;
    private final RoomRepository roomRepository;

    private final ReservationRepository reservationRepository;
    private final RoomResevationRepository roomResevationRepository;


    @Transactional
    public Reservation newReservation(ReservationRequest reservationRequest){
        Hotel hotel = hotelRepository.findById(reservationRequest.getHotelId()).orElseThrow(()->
                new IllegalStateException("Hotel not found"));
        AppUser user = appUserRepository.findById(reservationRequest.getUserId()).orElseThrow(()->
                new IllegalStateException("User not found"));

        Room room;

        switch (reservationRequest.getType()){
            case "once" :
                room = roomRepository.findByTypeRoomCategory(RoomType.ONCE, reservationRequest.getHotelId());
                break;
            case "twice" :
                room = roomRepository.findByTypeRoomCategory(RoomType.TWICE, reservationRequest.getHotelId());
                break;
            case "triple" :
                room = roomRepository.findByTypeRoomCategory(RoomType.TRIPLE, reservationRequest.getHotelId());
                break;
            case "fourth" :
                room = roomRepository.findByTypeRoomCategory(RoomType.FOURTH, reservationRequest.getHotelId());
                break;
            default:
                throw new IllegalStateException("Room type not exist");
        }
        System.out.println(room.toString());

        Reservation reservation = new Reservation();
        reservation.setAppUser(user);
        reservation.setStartDate(reservationRequest.getStartDate());
        reservation.setEndDate(reservationRequest.getEndDate());
        reservation.setTotal(room.getRoomCategory().getPrice() * reservationRequest.getNbChambre());
        reservation.setNbChambre(reservationRequest.getNbChambre());

        RoomResevation roomResevation = new RoomResevation(
                room,
                reservation,
                LocalDateTime.now()
        );

        reservation.getRoomResevations().add(roomResevation);

        room.setNbExisting(room.getNbExisting() - reservationRequest.getNbChambre());


        roomRepository.save(room);
        roomResevationRepository.save(roomResevation);
        reservationRepository.save(reservation);


        return reservation;
    }
}
