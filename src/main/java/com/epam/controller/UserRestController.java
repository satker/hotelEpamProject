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
    long add(@RequestBody AddUserDTO input) {
        if (userService.isUserExists(input)) {
            return 0;
        } else {
            userService.saveUser(input);
        }
        return 1;
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
