package com.epam.service;

import com.epam.dto.AddUserDTO;
import com.epam.dto.UserDTO;
import com.epam.mappers.UserMapper;
import com.epam.model.User;
import com.epam.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class UserServiceTest {
    private UserService userService;
    private UserRepository mockUserRepository;
    private UserMapper mockUserMapper;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Before
    public void setup() {
        mockUserRepository = mock(UserRepository.class);
        mockUserMapper = mock(UserMapper.class);
        bCryptPasswordEncoder = mock(BCryptPasswordEncoder.class);
        userService = new UserService(mockUserRepository, mockUserMapper, bCryptPasswordEncoder);
    }

    @Test
    public void save() {
        User user = InitialVariables.someUser();
        AddUserDTO addUserDTO = InitialVariables.someAddUserDTO();
        doReturn(user).when(mockUserMapper).addUserDtoToUser(addUserDTO);
        doReturn(user).when(mockUserRepository).save(user);
        userService.saveUser(addUserDTO);
    }

    @Test
    public void find() {
        User user = InitialVariables.someUser();
        UserDTO userDTO = InitialVariables.someUserDTO();
        doReturn(userDTO).when(mockUserMapper).userToUserDto(user);
        doReturn(Optional.of(user)).
                when(mockUserRepository).
                findById(any(Long.class));
        userService.findOne(user.getId());
    }

    @Test
    public void update() {
        User user = InitialVariables.someUser();
        doReturn(user).when(mockUserRepository).findOne(any(Long.class));
        doReturn(InitialVariables.someUserDTO()).when(mockUserMapper).userToUserDto(user);
        userService.updateUser(InitialVariables.someUserDTO(), 1L);

    }
}