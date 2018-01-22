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
        validateRoomType(appartmentsId);
        return ResponseEntity.ok(roomTypeService.findOne(appartmentsId));
    }

    @DeleteMapping(value = "/{appartmentsId}")
    public void deleteType(@PathVariable long appartmentsId) {
        validateRoomType(appartmentsId);
        roomTypeService.deleteRoomTypeById(appartmentsId);
    }

    @PostMapping
    void addType(@RequestBody RoomTypeDTO input) {
        roomTypeService.save(input);
    }

    private void validateRoomType(long roomTypeId) {
        this.roomTypeService.findRoomTypeById(roomTypeId).orElseThrow(
                () -> new RoomTypeRestController.RoomTypeNotFoundException(roomTypeId));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    private class RoomTypeNotFoundException extends RuntimeException {
        public RoomTypeNotFoundException(long roomTypeId) {
            super("could not find roomType '" + roomTypeId + "'.");
        }
    }
}
