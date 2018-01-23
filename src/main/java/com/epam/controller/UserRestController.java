package com.epam.controller;

import com.epam.dto.AddUserDTO;
import com.epam.dto.UserDTO;
import com.epam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
class UserRestController {

    private final UserService userService;

    @PostMapping
    UserDTO add(@RequestBody AddUserDTO input) {
        return userService.saveUser(input);
    }


    @GetMapping(value = "/{id}")
    public UserDTO getUser(@PathVariable("id") long id) {
        return userService.findOne(id);
    }

    @PutMapping(value = "/{id}")
    public UserDTO updateUser(@PathVariable("id") long id, @RequestBody UserDTO user) {
        return userService.updateUser(user, id);
    }
}
