package com.epam.dto;

import com.epam.model.Room;
import com.epam.model.RoomRequest;
import com.epam.model.User;
import lombok.Data;

@Data
public class RoomConfirmDTO {
    private UserDTO user;
    private RoomRequestDTO request;
    private RoomDTO room;
}
