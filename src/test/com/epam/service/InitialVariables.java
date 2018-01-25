package com.epam.service;

import com.epam.dto.AddUserDTO;
import com.epam.dto.UserDTO;
import com.epam.model.User;
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
}
