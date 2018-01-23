package com.epam.controller;

import com.epam.dto.*;
import com.epam.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminRestController {
    private final RoomConfirmService roomConfirmService;
    private final UserService userService;
    private final RoomRequestService roomRequestService;
    private final RoomService roomService;
    private final RoomTypeService roomTypeService;

    ////For admin
    @GetMapping(value = "/{idAdmin}")
    public UserDTO getValidateAdmin(@PathVariable("idAdmin") long id, HttpServletRequest request) {
        return userService.getUserValidateUser(id, request);
    }

    @PutMapping(value = "/{idAdmin}")
    public void updateValidateAdmin(@PathVariable("idAdmin") long id, @RequestBody UserDTO user, HttpServletRequest request) {
        userService.updateUserValidateUser(id, request, user);
    }

    //// For Users
    @GetMapping(value = "/{idAdmin}/users/{id}")
    public UserDTO getUser(@PathVariable("id") long id) {
        return userService.findOne(id);
    }

    @DeleteMapping(value = "/{idAdmin}/users/{id}")
    public void deleteUser(@PathVariable("id") long id) {
        userService.deleteUserById(id);
    }

    @DeleteMapping(value = "/{idAdmin}/users")
    public void deleteAllUsers() {
        userService.deleteAllUsers();
    }

    @GetMapping(value = "/{idAdmin}/users")
    List<UserDTO> getAllUsers() {
        return userService.findAllUsers();
    }

    //// For Confirms
    @PostMapping(value = "/{idAdmin}/users/{id}/confirms")
    ResponseEntity add(@PathVariable("id") long userId, @RequestBody RoomConfirmDTO input) {
        return this.userService
                .findUserById(userId)
                .map(account -> {
                    roomConfirmService.save(input);
                    return new ResponseEntity(null, HttpStatus.CREATED);
                }).get();
    }

    @GetMapping(value = "/{idAdmin}/users/{id}/confirms")
    List<RoomConfirmDTO> readRoomConfirms(@PathVariable("id") long userId) {
        return roomConfirmService.findByAccountUsername(userId);
    }

    //// For Requests
    @GetMapping(value = "/{idAdmin}/users/{id}/orders")
    List<RoomRequestDTO> readRoomRequests(@PathVariable("id") long userId) {
        return roomRequestService.findByAccountUsername(userId);
    }

    @GetMapping(value = "/{idAdmin}/users/{id}/orders/{orderId}")
    RoomRequestDTO readRoomRequest(@PathVariable("id") long userId, @PathVariable long orderId) {
        return roomRequestService.findOne(orderId);
    }

    @DeleteMapping(value = "/{idAdmin}/users/{id}/orders/{orderId}")
    public void deleteOrder(@PathVariable("orderId") long id) {
        roomRequestService.deleteRoomRequestById(id);
    }

    ///// For room type
    @GetMapping(value = "/{idAdmin}/appartments")
    List<RoomTypeDTO> findAllTypes() {
        return roomTypeService.findAllTypes();
    }

    @GetMapping(value = "/{idAdmin}/appartments/{appartmentsId}")
    RoomTypeDTO findTypeById(@PathVariable long appartmentsId) {
        return roomTypeService.findOne(appartmentsId);
    }

    @DeleteMapping(value = "/{idAdmin}/appartments/{appartmentsId}")
    public void deleteType(@PathVariable long appartmentsId) {
        roomTypeService.deleteRoomTypeById(appartmentsId);
    }

    @PostMapping(value = "/{idAdmin}/appartments")
    void addType(@RequestBody RoomTypeDTO input) {
        roomTypeService.save(input);
    }

    //// For Rooms
    @GetMapping(value = "/{idAdmin}/appartments/{appartmentsId}/rooms")
    List<RoomDTO> findRoomsByTypes(@PathVariable long appartmentsId) {
        return roomService.findRoomsByType(appartmentsId);
    }

    @GetMapping(value = "/{idAdmin}/appartments/{appartmentsId}/rooms/{roomsId}")
    RoomDTO readRoom(@PathVariable long roomsId) {
        return roomService.findOne(roomsId);
    }

    @DeleteMapping(value = "/{idAdmin}/appartments/{appartmentsId}/rooms/{roomsId}")
    public void deleteRoom(@PathVariable long roomsId) {
        roomService.deleteRoomById(roomsId);
    }

    @PostMapping(value = "/{idAdmin}/appartments/{appartmentsId}/rooms")
    public void addRoom(@RequestBody RoomDTO input) {
        roomService.save(input);
    }
}
