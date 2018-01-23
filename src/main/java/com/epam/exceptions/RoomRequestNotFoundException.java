package com.epam.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoomRequestNotFoundException extends RuntimeException {
    public RoomRequestNotFoundException(long roomRequestId) {
        super("could not find roomType '" + roomRequestId + "'.");
    }
}
