package com.epam.dto;

import com.epam.model.Room;
import com.epam.model.RoomRequest;
import com.epam.model.User;
import lombok.Data;

@Data
public class RoomConfirmDTO {
    private User user;
    private RoomRequest request;
    private Room room;
}
