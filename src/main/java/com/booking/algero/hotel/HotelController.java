package com.booking.algero.hotel;

import com.booking.algero.shared.CustomSearchResponse;
import com.booking.algero.shared.ImageUtils;
import com.booking.algero.shared.ResponseHandler;
import com.sun.imageio.plugins.common.ImageUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.Optional;

@RestController
@RequestMapping(path = "${v1Api}/hotel")
@AllArgsConstructor
public class HotelController {

    private final HotelRepository hotelRepository;
    private final HotelService hotelService;

    @GetMapping("/all")
    public ResponseEntity<Object> getAllHotels(){
        //return ResponseEntity.ok(hotelRepository.findAll());
      try {
            return ResponseHandler.response("List of hotels",
                    HttpStatus.ACCEPTED,
                    hotelRepository.findAll());
        }catch (Exception e){
            return ResponseHandler.response(e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    null);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addHotel(@RequestBody Hotel hotel){


        try {
            return ResponseHandler.response("List of hotels",
                    HttpStatus.ACCEPTED,
                    hotelRepository.save(hotel));
        }catch (Exception e){
            return ResponseHandler.response(e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    null);
        }
    }

    @PostMapping("/addImage/{id}")
    public String addImageToHotel(@PathVariable("id") Long id, @RequestParam("image") MultipartFile image) throws IOException {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        String path = "C:\\Users\\gtr2\\OneDrive\\Bureau\\images\\hotels\\"+image.getOriginalFilename();
        Files.copy(image.getInputStream(), Paths.get(path), StandardCopyOption.REPLACE_EXISTING);

        String url = "http://localhost:8080/api/v1/hotel/image/"+image.getOriginalFilename();

        hotel.get().setImageName(image.getOriginalFilename());
        hotel.get().setImageUrl(url);

        hotelRepository.save(hotel.get());

        return "Image done";
    }

    @GetMapping(path = "/image/{fileName}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    public byte[] getServerImage(@PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get("C:\\Users\\gtr2\\OneDrive\\Bureau\\images\\hotels\\" + fileName));
    }

    @PostMapping(value = "/addImage", consumes = { "multipart/form-data" })
    public String addImageTo( @RequestParam(value = "image", required = false) MultipartFile image,
                              @RequestParam(value = "hotel")  Hotel hotel) throws IOException {




        String encodedImage = Base64.getEncoder().encodeToString(image.getBytes());

        hotel.setImage(encodedImage);

        hotelRepository.save(hotel);

        return "Image done";
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Object> deleteHotel(@PathVariable("id") Long id){
        try {


            Hotel hotel = hotelRepository.findById(id).orElseThrow(
                    ()-> new IllegalStateException("Hotel not found"));
            hotelRepository.delete(hotel);


            return ResponseHandler.response("Delete done",
                    HttpStatus.ACCEPTED,
                    hotelRepository.findAll());
        }catch (Exception e){
            return ResponseHandler.response(e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    null);
        }
    }

    @GetMapping("/getbywilaya/{id}")
    public ResponseEntity<Object> getHotelsByWilayaID(@PathVariable("id") Long id){
        try {
            return ResponseHandler.response("Hotels by wilaya id : "+id,
                    HttpStatus.ACCEPTED,
                    hotelRepository.findHotelsByWilayaID(id));
        }catch (Exception e){
            return ResponseHandler.response(e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    null);
        }
    }

    @GetMapping(value = "pdf" )
    public ResponseEntity<Object> getPDFfile() throws IOException {
        String encodedFile =  Base64
                .getEncoder()
                .encodeToString(Files.readAllBytes(Paths.get("C:\\Users\\gtr2\\OneDrive\\Bureau\\images\\hotels\\output.pdf" )));

        try {
            return ResponseHandler.response("pdf file ",
                    HttpStatus.ACCEPTED,
                    encodedFile);
        }catch (Exception e){
            return ResponseHandler.response(e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    null);
        }

    }

    @PostMapping("/customsearch")
    public ResponseEntity<Object> getHotelsByCustomSearch(@RequestBody CustomSearchResponse response){
        try {
            return ResponseHandler.response("Hotels by custom search id ",
                    HttpStatus.ACCEPTED,
                    hotelService.findHotelsByCustomSearch(response));
        }catch (Exception e){
            return ResponseHandler.response(e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    null);
        }
    }




}
