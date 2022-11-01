package com.booking.algero.wilaya;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WilayaService {

    private final WilayaRepository wilayaRepository;

    public List<Wilaya> getWilayWithNbHotels(){
        List<Wilaya> wilayas = wilayaRepository.findAll();
        wilayas.stream()
                .forEach(wilaya -> wilaya.setNbHotels(
                        wilayaRepository.getNumberOfHotelsById(wilaya.getId())
                ));
        return  wilayas;
    }
}
