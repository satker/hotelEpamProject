package com.epam.controller;

import com.epam.dto.RoomDTO;
import com.epam.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("appartments/{appartmentsId}/rooms")
@RequiredArgsConstructor
public class RoomRestController {
    private final RoomService roomService;

    @GetMapping
    ResponseEntity<List<RoomDTO>> findRoomsByTypes(@PathVariable long appartmentsId) {
        return ResponseEntity.ok(roomService.findRoomsByType(appartmentsId));
    }

    @GetMapping(value = "/{roomsId}")
    ResponseEntity<RoomDTO> readRoom(@PathVariable long roomsId) {
        return ResponseEntity.ok(roomService.findOne(roomsId));
    }

    @DeleteMapping(value = "/{roomsId}")
    public ResponseEntity deleteRoom(@PathVariable long roomsId) {
        roomService.deleteRoomById(roomsId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    ResponseEntity addRoom(@PathVariable long userId, @RequestBody RoomDTO input, @PathVariable long appartmentsId) {
        roomService.save(input);
        return new ResponseEntity(null, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    private class RoomNotFoundException extends RuntimeException {
        RoomNotFoundException(long userId) {
            super("could not find room with id = " + userId + "'.");
        }
    }
}
