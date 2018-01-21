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
    ResponseEntity<RoomDTO> readRoom(@PathVariable long appartmentsId, @PathVariable long roomsId) {
        validateRoom(roomsId);
        return ResponseEntity.ok(roomService.findOne(appartmentsId));
    }

    @DeleteMapping(value = "/{roomsId}")
    public void deleteRoom(@PathVariable long roomsId) {
        validateRoom(roomsId);
        roomService.deleteRoomById(roomsId);
    }

    @PostMapping
    public void addRoom(@PathVariable long userId, @RequestBody RoomDTO input, @PathVariable long appartmentsId) {
        roomService.save(input);
    }

    private void validateRoom(long roomId) {
        this.roomService.findRoomById(roomId).orElseThrow(
                () -> new RoomRestController.RoomNotFoundException(roomId));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    private class RoomNotFoundException extends RuntimeException {
        RoomNotFoundException(long roomId) {
            super("could not find room with id = " + roomId + "'.");
        }
    }
}
