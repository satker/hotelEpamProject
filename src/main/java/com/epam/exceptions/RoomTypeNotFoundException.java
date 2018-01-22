package com.epam.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoomTypeNotFoundException extends RuntimeException {
    public RoomTypeNotFoundException(long roomTypeId) {
        super("could not find roomType '" + roomTypeId + "'.");
    }
}
