package com.booking.algero.room;

import com.booking.algero.roomCategory.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query(name = "findByTypeRoomCategory",
            value = "SELECT r from Room r , RoomCategory rc, Hotel h where r.roomCategory.roomType = :roomType and r.hotel.id = :hotelId")
    Room findByTypeRoomCategory(@Param("roomType") RoomType roomType, @Param("hotelId") Long hotelId);
}