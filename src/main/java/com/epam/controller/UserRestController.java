package com.epam.controller;

import com.epam.model.User;
import com.epam.service.UserService;
import com.epam.util.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/users")
class UserRestController {

    private final UserService userService;

    @Autowired
    UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    ResponseEntity<?> add(@RequestBody User input) {
        ResponseEntity responseEntity;
        if (userService.isUserExists(input)) {
            responseEntity = new ResponseEntity(new CustomErrorType("Unable to create. A User with name " +
                    input.getLogin() + " already exist."), HttpStatus.CONFLICT);
        }
        else {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(input.getId()).toUri());
            responseEntity = new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
        }
        return responseEntity;

    }

    @GetMapping()
    ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userService.findAllUsers();
        ResponseEntity responseEntity;
        if (users.isEmpty()) {
            responseEntity = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        else
        responseEntity = new ResponseEntity<List<User>>(users, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") long id) {
        User user = userService.fineOne(id);
        ResponseEntity responseEntity;
        if (user == null) {
            responseEntity = new ResponseEntity(new CustomErrorType("User with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        else
        responseEntity = new ResponseEntity<User>(user, HttpStatus.OK);
        return responseEntity;
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        User currentUser = userService.fineOne(id);
        ResponseEntity responseEntity;
        if (currentUser == null) {
            responseEntity = new ResponseEntity(new CustomErrorType("Unable to update. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        else {
            currentUser.setFirstName(user.getFirstName());
            currentUser.setLastName(user.getLastName());
            currentUser.setLogin(user.getLogin());
            currentUser.setPassword(user.getPassword());
            userService.updateUser(currentUser);
            responseEntity = new ResponseEntity<User>(currentUser, HttpStatus.OK);
        }
        return responseEntity;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
        User user = userService.fineOne(id);
        ResponseEntity responseEntity;
        if (user == null) {
            responseEntity = new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        else {
            userService.deleteUserById(id);
            responseEntity = new ResponseEntity<User>(HttpStatus.NO_CONTENT);
        }
        return responseEntity;
    }

    @DeleteMapping()
    public ResponseEntity<User> deleteAllUsers() {
        userService.deleteAllUsers();
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);

    }


}

