package com.booking.algero.wilaya;

import com.booking.algero.hotel.Hotel;
import com.booking.algero.shared.ResponseHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

@RestController
@RequestMapping(value = "${v1Api}/wilaya")
@AllArgsConstructor
public class WilayaController {
    private final WilayaService wilayaService;

    private final WilayaRepository wilayaRepository;

    @GetMapping
    public ResponseEntity<Object> getAllWilayas(){
        try{


            return ResponseHandler.response(
                    "List of wilayas",
                    HttpStatus.ACCEPTED,
                    wilayaRepository.findAll()
            );
        }catch (Exception e){
            return ResponseHandler.response(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    null);
        }
    }

    @GetMapping(value = "/withNbHotels")
    public ResponseEntity<Object> getAllWilayasWithNbHotels(){
        try{


            return ResponseHandler.response(
                    "List of wilayas with number of hotels",
                    HttpStatus.ACCEPTED,
                    wilayaService.getWilayWithNbHotels()
            );
        }catch (Exception e){
            return ResponseHandler.response(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    null);
        }
    }

    @PostMapping(value = "add")
    public ResponseEntity<Object> addWilaya(@RequestBody Wilaya wilaya){
        try{


            return ResponseHandler.response(
                    "Wilaya added",
                    HttpStatus.CREATED,
                    wilayaRepository.save(wilaya)
            );
        }catch (Exception e){
            return ResponseHandler.response(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    null);
        }
    }

    @PostMapping("/addImage/{id}")
    public String addImageToHotel(@PathVariable("id") Long id, @RequestParam("image") MultipartFile image) throws IOException {
        Optional<Wilaya> hotel = wilayaRepository.findById(id);

        String encodedImage = Base64.getEncoder().encodeToString(image.getBytes());

        hotel.get().setImage(encodedImage);

        wilayaRepository.save(hotel.get());

        return "Image done";
    }

}
