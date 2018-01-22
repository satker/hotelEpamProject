package com.epam.controller;

import com.epam.dto.RoomTypeDTO;
import com.epam.service.RoomTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("appartments")
@RequiredArgsConstructor
public class RoomTypeRestController {
    private final RoomTypeService roomTypeService;

    @GetMapping
    List<RoomTypeDTO> findAllTypes() {
        return roomTypeService.findAllTypes();
    }

    @GetMapping(value = "/{appartmentsId}")
    RoomTypeDTO findTypeById(@PathVariable long appartmentsId) {
        return roomTypeService.findOne(appartmentsId);
    }

    @DeleteMapping(value = "/{appartmentsId}")
    public void deleteType(@PathVariable long appartmentsId) {
        roomTypeService.deleteRoomTypeById(appartmentsId);
    }

    @PostMapping
    void addType(@RequestBody RoomTypeDTO input) {
        roomTypeService.save(input);
    }
}
