package com.epam.controller;

import com.epam.dto.RoomDTO;
import com.epam.service.RoomService;
import com.epam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("users/{userId}/rooms")
@RequiredArgsConstructor
public class RoomRestController {
    private final RoomService roomService;
    private final UserService userService;

    @GetMapping
    ResponseEntity<Collection<RoomDTO>> readRooms(@PathVariable long userId) {
        this.validateUser(userId);
        return ResponseEntity.ok(roomService.findAll());
    }

    private void validateUser(long userId) {
        this.userService.findUserById(userId).orElseThrow(
                () -> new RoomRequestNotFoundException(userId));
    }
}
