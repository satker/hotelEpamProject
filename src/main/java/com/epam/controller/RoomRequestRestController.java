package com.epam.controller;

import com.epam.dto.RoomRequestDTO;
import com.epam.service.RoomRequestService;
import com.epam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;

@RestController
@RequestMapping("users/{userId}/orders")
@RequiredArgsConstructor
class RoomRequestRestController {

    private final RoomRequestService roomRequestService;

    private final UserService userService;

    @PostMapping
    ResponseEntity<?> add(@PathVariable long userId, @RequestBody RoomRequestDTO input) {
        this.validateUser(userId);
        return this.userService
                .findUserById(userId)
                .map(account -> {
                    roomRequestService.save(input);
                    HttpHeaders httpHeaders = new HttpHeaders();
                    httpHeaders.setLocation(ServletUriComponentsBuilder
                            .fromCurrentRequest().path("/{id}")
                            .buildAndExpand(roomRequestService.getId(input)).toUri().toUri());
                    return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
                }).get();
    }

    @GetMapping(value = "/{orderId}")
    RoomRequestDTO readRoomRequest(@PathVariable long userId, @PathVariable Long requestId) {
        this.validateUser(userId);
        return ResponseEntity.ok(roomRequestService.findOne(requestId));
    }

    @GetMapping
    Collection<RoomRequestDTO> readRoomRequests(@PathVariable long userId) {
        this.validateUser(userId);
        return ResponseEntity.ok(roomRequestService.findByAccountUsername(userId));
    }


    private void validateUser(long userId) {
        this.userService.findUserById(userId).orElseThrow(
                () -> new UserNotFoundException(userId));
    }
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(long userId) {
        super("could not find user '" + userId + "'.");
    }
}
