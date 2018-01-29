package com.epam.controller;

import com.epam.dto.AddUserDTO;
import com.epam.dto.UserDTO;
import com.epam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
class UserRestController {
    private final UserService userService;

    @PostMapping
    ResponseEntity add(@RequestBody AddUserDTO input) {
        userService.saveUser(input);
        return new ResponseEntity(null, HttpStatus.CREATED);
    }

    @GetMapping
    public UserDTO getValidateUser(Principal principal) {
        return userService.findUserByLogin(principal.getName());
    }

    @GetMapping(value = "/{id}")
    public UserDTO getValidateUser(@PathVariable("id") long id, Principal principal) {
        return userService.getUserValidateUser(id, principal.getName());
    }

    @PutMapping(value = "/{id}")
    public void updateValidateUser(@PathVariable("id") long id, @RequestBody UserDTO user, Principal principal) {
        userService.updateUserValidateUser(id, principal.getName(), user);
    }
}
