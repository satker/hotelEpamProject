package com.epam.controller;

import com.epam.dto.UserDTO;
import com.epam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
class UserRestController {

    private final UserService userService;

    @PostMapping
    long add(@RequestBody UserDTO input) {
        if (userService.isUserExists(input)) {
            return 0;
        } else {
            userService.saveUser(input);
        }
        return userService.getId(input);
    }

    @GetMapping
    ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") long id) {
        validateUser(id);
        return ResponseEntity.ok(userService.findOne(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") long id, @RequestBody UserDTO user) {
        validateUser(id);
        return ResponseEntity.ok(userService.updateUser(user, id));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable("id") long id) {
        validateUser(id);
        userService.deleteUserById(id);
    }

    @DeleteMapping
    public void deleteAllUsers() {
        userService.deleteAllUsers();
    }

    private void validateUser(long userId) {
        this.userService.findUserById(userId).orElseThrow(
                () -> new UserRestController.UserNotFoundException(userId));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    private class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(long userId) {
            super("could not find user '" + userId + "'.");
        }
    }
}

