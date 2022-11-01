package com.booking.algero.hotel;

import com.booking.algero.wilaya.Wilaya;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Entity
@Table(name = "hotel")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Hotel {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String image;


    private String imageUrl;
    private String imageName;


    private String address;
    private String zipcode;
    private String tel;
    private String webSite;
    private String ownerTel;
    private String ownerFirstname;
    private String ownerLastname;

    @ManyToOne
    private Wilaya wilaya;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}