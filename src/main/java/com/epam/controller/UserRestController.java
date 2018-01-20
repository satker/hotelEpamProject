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

    @PostMapping()
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

    @GetMapping()
    ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.findAllUsers();
        ResponseEntity responseEntity;
        if (users.isEmpty()) {
            responseEntity = new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            responseEntity = new ResponseEntity<List<UserDTO>>(users, HttpStatus.OK);
        }
        return responseEntity;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") long id) {
        UserDTO user = userService.fineOne(id);
        ResponseEntity responseEntity;
        responseEntity = new ResponseEntity<UserDTO>(user, HttpStatus.OK);
        return responseEntity;
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody UserDTO user) {
        ResponseEntity responseEntity;
        responseEntity = new ResponseEntity<UserDTO>(userService.updateUser(user, id), HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
        ResponseEntity responseEntity;
        userService.deleteUserById(id);
        responseEntity = new ResponseEntity<UserDTO>(HttpStatus.NO_CONTENT);
        return responseEntity;
    }

    @DeleteMapping()
    public ResponseEntity<UserDTO> deleteAllUsers() {
        userService.deleteAllUsers();
        return new ResponseEntity<UserDTO>(HttpStatus.NO_CONTENT);

    }


}

