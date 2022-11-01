package com.booking.algero.roomCategory;

import com.booking.algero.shared.ResponseHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "${v1Api}/roomcategory")
@AllArgsConstructor
public class RoomCategoryController {
    private final RoomCategoryRepository roomCategoryRepository;

    @GetMapping
    public ResponseEntity<Object> getAllRoomCategory(){
        try{

            return ResponseHandler.response(
                    "List of rooms category",
                    HttpStatus.ACCEPTED,
                    roomCategoryRepository.findAll()
            );
        }catch(Exception e){
            return ResponseHandler.response(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    null
            );
        }
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Object> addRoomCategory(@RequestBody RoomCategory roomCategory){
        try{

            return ResponseHandler.response(
                    "Room category added",
                    HttpStatus.CREATED,
                    roomCategoryRepository.save(roomCategory)
            );
        }catch(Exception e){
            return ResponseHandler.response(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    null
            );
        }
    }

}
