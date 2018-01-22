package com.epam.controller;

import com.epam.dto.RoomConfirmDTO;
import com.epam.service.RoomConfirmService;
import com.epam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users/{userId}/confirms")
@RequiredArgsConstructor
public class RoomConfirmRestController {
    private final RoomConfirmService roomConfirmService;
    private final UserService userService;

    @PostMapping
    ResponseEntity add(@PathVariable long userId, @RequestBody RoomConfirmDTO input) {
        return this.userService
                .findUserById(userId)
                .map(account -> {
                    roomConfirmService.save(input);
                    return new ResponseEntity(null, HttpStatus.CREATED);
                }).get();
    }

    @GetMapping
    List<RoomConfirmDTO> readRoomConfirms(@PathVariable long userId) {
        return roomConfirmService.findByAccountUsername(userId);
    }

    @GetMapping(value = "/{confirmsId}")
    RoomConfirmDTO readRoomRequest(@PathVariable long userId) {
        return roomConfirmService.findOne(userId);
    }
}



