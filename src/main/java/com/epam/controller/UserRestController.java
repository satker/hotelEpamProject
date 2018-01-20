package com.epam.controller;

import com.epam.dto.UserDTO;
import com.epam.service.UserService;
import com.epam.util.CustomErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
class UserRestController {

    private final UserService userService;

    @PostMapping
    ResponseEntity<?> add(@RequestBody UserDTO input) {
        ResponseEntity responseEntity;
        if (userService.isUserExists(input)) {
            responseEntity = new ResponseEntity(new CustomErrorType("Unable to create. A User with name " +
                    input.getLogin() + " already exist."), HttpStatus.CONFLICT);
        } else {
            userService.saveUser(input);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(userService.getId(input)).toUri());
            responseEntity = new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
        }
        return responseEntity;
    }

    @GetMapping
    ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.fineOne(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") long id, @RequestBody UserDTO user) {
        return new ResponseEntity<UserDTO>(userService.updateUser(user, id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable("id") long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<UserDTO>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<UserDTO> deleteAllUsers() {
        userService.deleteAllUsers();
        return new ResponseEntity<UserDTO>(HttpStatus.NO_CONTENT);

    }


}

