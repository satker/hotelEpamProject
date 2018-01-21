package com.epam.controller;

import com.epam.dto.RoomTypeDTO;
import com.epam.service.RoomTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("appartments")
@RequiredArgsConstructor
public class RoomTypeRestController {
    private final RoomTypeService roomTypeService;

    @GetMapping
    ResponseEntity<Collection<RoomTypeDTO>> findAllTypes() {
        return ResponseEntity.ok(roomTypeService.findAllTypes());
    }

    @GetMapping(value = "/{appartmentsId}")
    ResponseEntity<RoomTypeDTO> findTypeById(@PathVariable long appartmentsId) {
        return ResponseEntity.ok(roomTypeService.findOne(appartmentsId));
    }

    @DeleteMapping(value = "/{appartmentsId}")
    public ResponseEntity deleteType(@PathVariable long appartmentsId) {
        roomTypeService.deleteRoomTypeById(appartmentsId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    ResponseEntity addType(@RequestBody RoomTypeDTO input) {
        roomTypeService.save(input);
        return new ResponseEntity(null, HttpStatus.CREATED);
    }
}
