package com.epam.controller;

import com.epam.dto.RoomRequestDTO;
import com.epam.service.RoomRequestService;
import com.epam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("user/{userId}/orders")
@RequiredArgsConstructor
class RoomRequestRestController {

    private final RoomRequestService roomRequestService;

    private final UserService userService;

    @PostMapping
    ResponseEntity add(@PathVariable long userId, @RequestBody RoomRequestDTO input) {
        return this.userService
                .findUserById(userId)
                .map(account -> {
                    roomRequestService.save(input);
                    return new ResponseEntity(null, HttpStatus.CREATED);
                }).get();
    }

    @GetMapping(value = "/{orderId}")
    RoomRequestDTO readRoomRequest(@PathVariable long userId, @PathVariable long orderId) {
        return roomRequestService.findOne(orderId);
    }

    @GetMapping
    List<RoomRequestDTO> readRoomRequests(@PathVariable long userId) {
        return roomRequestService.findByAccountUsername(userId);
    }

    @DeleteMapping(value = "/{orderId}")
    public void deleteOrder(@PathVariable("orderId") long id) {
        roomRequestService.deleteRoomRequestById(id);
    }
}
