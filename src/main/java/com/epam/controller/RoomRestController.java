package com.epam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users/{userId}/rooms")
@RequiredArgsConstructor
public class RoomRestController {

}
