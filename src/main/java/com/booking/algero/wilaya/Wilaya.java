package com.booking.algero.wilaya;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "wilaya")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Wilaya {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String image;

    private String wilayaName;
    private String matricul;

    @Transient
    private Integer nbHotels = null;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}