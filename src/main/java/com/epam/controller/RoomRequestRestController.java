package com.epam.controller;

import com.epam.dto.RoomRequestDTO;
import com.epam.service.RoomRequestService;
import com.epam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("users/{userId}/orders")
@RequiredArgsConstructor
class RoomRequestRestController {

    private final RoomRequestService roomRequestService;

    private final UserService userService;

    @PostMapping
    ResponseEntity add(@PathVariable long userId, @RequestBody RoomRequestDTO input) {
        this.validateUser(userId);
        return this.userService
                .findUserById(userId)
                .map(account -> {
                    roomRequestService.save(input);
                    return new ResponseEntity(null, HttpStatus.CREATED);
                }).get();
    }

    @GetMapping(value = "/{orderId}")
    ResponseEntity<RoomRequestDTO> readRoomRequest(@PathVariable long userId, @PathVariable long orderId) {
        this.validateUser(userId);
        return ResponseEntity.ok(roomRequestService.findOne(orderId));
    }

    @GetMapping
    ResponseEntity<Collection<RoomRequestDTO>> readRoomRequests(@PathVariable long userId) {
        this.validateUser(userId);
        return ResponseEntity.ok(roomRequestService.findByAccountUsername(userId));
    }

    @DeleteMapping(value = "/{orderId}")
    public ResponseEntity<RoomRequestDTO> deleteOrder(@PathVariable("orderId") long id) {
        roomRequestService.deleteRoomRequestById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    private void validateUser(long userId) {
        this.userService.findUserById(userId).orElseThrow(
                () -> new RoomRequestNotFoundException(userId));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    private class RoomRequestNotFoundException extends RuntimeException {
        RoomRequestNotFoundException(long userId) {
            super("could not find user '" + userId + "'.");
        }
    }

}
