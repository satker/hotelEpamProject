package com.epam.service;

import com.epam.dto.AddUserDTO;
import com.epam.dto.UserDTO;
import com.epam.model.User;

class InitialVariables {
    static final AddUserDTO ADD_USER_DTO;
    static final UserDTO USER_DTO;
    static final User USER;

    static {
        ADD_USER_DTO = new AddUserDTO();
        ADD_USER_DTO.setLogin("jakson____den");
        ADD_USER_DTO.setLastName("Kunats");
        ADD_USER_DTO.setFirstName("Artem");
        ADD_USER_DTO.setPassword("1111");

        USER_DTO = new UserDTO();
        USER_DTO.setRole("ROLE_USER");
        USER_DTO.setPassword("1111");
        USER_DTO.setLogin("jakson____den");
        USER_DTO.setLastName("Kunats");
        USER_DTO.setFirstName("Artem");
        USER_DTO.setId(1L);

        USER = new User();
        USER.setFirstName("Artem");
        USER.setId(1L);
        USER.setLastName("Kunats");
        USER.setLogin("jakson____den");
        USER.setPassword("1111");
        USER.setRole("ROLE_USER");
    }
}
