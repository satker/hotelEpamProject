package com.epam.mappers;

import com.epam.dto.AddUserDTO;
import com.epam.dto.UserDTO;
import com.epam.model.User;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    public abstract UserDTO userToUserDto(User user);

    public abstract AddUserDTO userToAddUserDto(User user);

    public abstract List<UserDTO> usersToUsersDto(List<User> users);

    public abstract User userDtoToUser(UserDTO user);

    public abstract User addUserDtoToUser(AddUserDTO user);

    public abstract List<User> usersDtoToUsers(List<UserDTO> users);
}
