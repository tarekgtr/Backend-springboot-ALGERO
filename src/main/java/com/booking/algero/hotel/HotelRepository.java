package com.booking.algero.hotel;

import com.booking.algero.roomCategory.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    long countHotelsByWilayaIsLike(Long id);

    @Query(name = "findHotelsByWilayaID", value = "select h from Hotel h, Wilaya w  where w.id=:wilayaid and w.id = h.wilaya.id")
    List<Hotel> findHotelsByWilayaID(@Param("wilayaid") Long id);

    //    @Query(name = "findHotelsByCustomParams", value = "select h from Hotel h where" +
//
//            "  h.wilaya.id =:wilayaid" +
//
//            " and Room.hotel.id = h.id" +
//
//            " and Room.roomCategory.id = :roomCategoryId"
//
//             )
    @Query(name = "findHotelsByCustomParams", value = "SELECT h FROM Hotel h" +

            " INNER JOIN Wilaya w on h.wilaya.id = w.id" +
            " INNER JOIN Room r on h.id = r.hotel.id" +
            " inner join RoomCategory rc on rc.id = r.roomCategory.id" +
            " where " +
            " rc.id = :roomCategoryId" +
            " and h.wilaya.id = :wilayaid" +
            " and h.name like :searchText")
    List<Hotel> findHotelsByCustomParams(@Param("wilayaid") Long wilayaid,
                                         @Param("roomCategoryId") Long roomCategoryId,
                                         @Param("searchText") String searchText);
}