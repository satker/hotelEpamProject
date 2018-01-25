package com.epam.service;

import com.epam.dto.AddUserDTO;
import com.epam.dto.UserDTO;
import com.epam.mappers.UserMapper;
import com.epam.model.User;
import com.epam.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.junit.Assert.*;

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
        UserDTO userDTO = InitialVariables.someUserDTO();
        User user = InitialVariables.someUser();
        AddUserDTO addUserDTO = InitialVariables.someAddUserDTO();

        doReturn(user).when(mockUserMapper).addUserDtoToUser(addUserDTO);
        doReturn(userDTO).when(mockUserMapper).userToUserDto(user);
        doReturn(user).when(mockUserRepository).save(user);

        UserDTO userDTOResult = userService.saveUser(addUserDTO);

        verify(mockUserMapper).addUserDtoToUser(addUserDTO);
        verify(mockUserRepository).save(user);
        verify(mockUserMapper).userToUserDto(user);

        assertEquals(userDTOResult, userDTO);

    }

    @Test
    public void find() {
        User user = InitialVariables.someUser();
        UserDTO userDTO = InitialVariables.someUserDTO();

        doReturn(userDTO).when(mockUserMapper).userToUserDto(user);
        doReturn(Optional.of(user)).when(mockUserRepository).findById(any(Long.class));

        UserDTO userDTOResult = userService.findOne(user.getId());

        verify(mockUserMapper).userToUserDto(user);
        verify(mockUserRepository).findById(any(Long.class));

        assertEquals(userDTOResult, userDTO);
    }

    @Test
    public void update() {
        User user = InitialVariables.someUser();
        UserDTO userDTO = InitialVariables.someUserDTO();

        doReturn(user).when(mockUserRepository).findOne(any(Long.class));
        doReturn(userDTO).when(mockUserMapper).userToUserDto(user);
        doReturn(user).when(mockUserRepository).save(user);

        UserDTO userResult = userService.updateUser(userDTO, 1L);

        verify(mockUserRepository).findOne(any(Long.class));
        verify(mockUserMapper).userToUserDto(user);
        verify(mockUserRepository).save(user);

        assertEquals(userResult, userDTO);

    }
}