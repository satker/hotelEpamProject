package com.epam.mappers;

import com.epam.dto.UserDTO;
import com.epam.model.User;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {


    UserDTO userToUserDto(User user);

    List<UserDTO> usersToUsersDto(List<User> users);

    User userDtoToUser(UserDTO user);

    List<User> usersDtoToUsers(List<UserDTO> users);
}
