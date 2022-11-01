package com.booking.algero.roomCategory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "room_category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomCategory {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private double price;
    private RoomType roomType;



















    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}