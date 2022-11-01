package com.booking.algero.wilaya;

import com.booking.algero.wilaya.Wilaya;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Repository
@Transactional(readOnly = true)
public interface WilayaRepository extends JpaRepository<Wilaya, Long> {

    @Query(name = "getNumberOfHotelsById", value = "SELECT count(h) from Hotel h, Wilaya w where w.id = :wilayaid and w.id = h.wilaya.id")
    int getNumberOfHotelsById(@Param("wilayaid") Long wilayaid);





}