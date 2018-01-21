package com.epam.controller;

import com.epam.dto.RoomDTO;
import com.epam.service.RoomService;
import com.epam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("users/{userId}/rooms")
@RequiredArgsConstructor
public class RoomRestController {
    private final RoomService roomService;
    private final UserService userService;

    @PostMapping
    ResponseEntity add(@PathVariable long userId, @RequestBody RoomDTO input) {
        this.validateUser(userId);
        return this.userService
                .findUserById(userId)
                .map(account -> {
                    roomService.saveRoom(input);
                    return new ResponseEntity(null, HttpStatus.CREATED);
                }).get();
    }

    @GetMapping
    ResponseEntity<Collection<RoomDTO>> readRooms(@PathVariable long userId) {
        this.validateUser(userId);
        return ResponseEntity.ok(roomService.findAll());
    }

    @GetMapping(value = "/{roomsId}")
    ResponseEntity<RoomDTO> readRoomRequest(@PathVariable long userId, @PathVariable long roomsId) {
        this.validateUser(userId);
        return ResponseEntity.ok(roomService.findOne(roomsId));
    }

    private void validateUser(long userId) {
        this.userService.findUserById(userId).orElseThrow(
                () -> new RoomNotFoundException(userId));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    private class RoomNotFoundException extends RuntimeException {
        public RoomNotFoundException(long userId) {
            super("could not find user '" + userId + "'.");
        }
    }
}
