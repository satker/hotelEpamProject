package com.epam.service;

import com.epam.dto.*;
import com.epam.model.*;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;

class InitialVariables {
    private static final EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
            .stringLengthRange(5, 50)
            .build();

    public static User someUser() {
        return random.nextObject(User.class);
    }

    public static AddUserDTO someAddUserDTO(){
        return random.nextObject(AddUserDTO.class);
    }

    public static UserDTO someUserDTO(){
        return random.nextObject(UserDTO.class);
    }

    public static RoomType someRoomType(){
        return random.nextObject(RoomType.class);
    }

    public static RoomTypeDTO someRoomTypeDTO(){
        return random.nextObject(RoomTypeDTO.class);
    }

    public static Room someRoom(){
        return random.nextObject(Room.class);
    }

    public static RoomDTO someRoomDTO(){
        return random.nextObject(RoomDTO.class);
    }

    public static RoomRequest someRoomRequest(){
        return random.nextObject(RoomRequest.class);
    }

    public static RoomRequestDTO someRoomRequestDTO(){
        return random.nextObject(RoomRequestDTO.class);
    }

    public static RoomConfirm someRoomConfirm(){
        return random.nextObject(RoomConfirm.class);
    }

    public static RoomConfirmDTO someRoomConfirmDTO(){
        return random.nextObject(RoomConfirmDTO.class);
    }
}
