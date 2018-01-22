package com.epam.controller;

import com.epam.dto.RoomDTO;
import com.epam.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("appartments/{appartmentsId}/rooms")
@RequiredArgsConstructor
public class RoomRestController {
    private final RoomService roomService;

    @GetMapping
    List<RoomDTO> findRoomsByTypes(@PathVariable long appartmentsId) {
        return roomService.findRoomsByType(appartmentsId);
    }

    @GetMapping(value = "/{roomsId}")
    RoomDTO readRoom(@PathVariable long roomsId) {
        return roomService.findOne(roomsId);
    }

    @DeleteMapping(value = "/{roomsId}")
    public void deleteRoom(@PathVariable long roomsId) {
        roomService.deleteRoomById(roomsId);
    }

    @PostMapping
    public void addRoom(@RequestBody RoomDTO input) {
        roomService.save(input);
    }
}
