package com.epam.controller;

import com.epam.dto.AddUserDTO;
import com.epam.dto.UserDTO;
import com.epam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    public UserDTO getValidateUser(@PathVariable("id") long id, HttpServletRequest request) {
        return userService.getUserValidateUser(id, request);
    }

    @PutMapping(value = "/{id}")
    public void updateValidateUser(@PathVariable("id") long id, @RequestBody UserDTO user, HttpServletRequest request) {
        userService.updateUserValidateUser(id, request, user);
    }
}
