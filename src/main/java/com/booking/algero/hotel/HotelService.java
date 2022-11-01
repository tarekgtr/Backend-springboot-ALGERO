package com.booking.algero.hotel;

import com.booking.algero.shared.CustomSearchResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HotelService {
    private final HotelRepository hotelRepository;

    public List<Hotel> findHotelsByCustomSearch(CustomSearchResponse response){
        String search = "%"+response.getSearchText()+"%";
        System.out.println("------------------------->>>"+response.getWilayaId()+"    " +response.getRoomCategoryId()+"   "+search);
        return hotelRepository.findHotelsByCustomParams(response.getWilayaId(), response.getRoomCategoryId(), search);
    }
}
