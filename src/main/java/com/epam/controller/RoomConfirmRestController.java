package com.epam.controller;

import com.epam.dto.RoomConfirmDTO;
import com.epam.service.RoomConfirmService;
import com.epam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("users/{userId}/confirms")
@RequiredArgsConstructor
public class RoomConfirmRestController {
    private final RoomConfirmService roomConfirmService;
    private final UserService userService;

    @PostMapping
    ResponseEntity add(@PathVariable long userId, @RequestBody RoomConfirmDTO input) {
        this.validateUser(userId);
        return this.userService
                .findUserById(userId)
                .map(account -> {
                    roomConfirmService.save(input);
                    return new ResponseEntity(null, HttpStatus.CREATED);
                }).get();
    }

    @GetMapping
    ResponseEntity<Collection<RoomConfirmDTO>> readRoomConfirms(@PathVariable long userId) {
        this.validateUser(userId);
        return ResponseEntity.ok(roomConfirmService.findByAccountUsername(userId));
    }

    @GetMapping(value = "/{confirmsId}")
    ResponseEntity<RoomConfirmDTO> readRoomRequest(@PathVariable long userId, @PathVariable long confirmsId) {
        this.validateUser(userId);
        return ResponseEntity.ok(roomConfirmService.findOne(confirmsId));
    }

    private void validateUser(long userId) {
        this.userService.findUserById(userId).orElseThrow(
                () -> new RoomConfirmNotFoundException(userId));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    private class RoomConfirmNotFoundException extends RuntimeException {
        RoomConfirmNotFoundException(long userId) {
            super("could not find user '" + userId + "'.");
        }
    }

}
