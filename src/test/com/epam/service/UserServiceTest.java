package com.epam.service;

import com.epam.mappers.UserMapper;
import com.epam.model.User;
import com.epam.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.Answer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static com.epam.service.InitialVariables.*;
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
        doReturn(USER).when(mockUserMapper).addUserDtoToUser(ADD_USER_DTO);
        doReturn(USER).when(mockUserRepository).save(USER);
        userService.saveUser(ADD_USER_DTO);
    }

    @Test
    public void find() {
        doReturn(USER_DTO).when(mockUserMapper).userToUserDto(USER);
        doReturn(Optional.of(USER)).
                when(mockUserRepository).
                findById(any(Long.class));
        userService.findOne(1L);
    }

    @Test
    public void update(){
        doReturn(USER).when(mockUserRepository).findOne(any(Long.class));
        doReturn(USER_DTO).when(mockUserMapper).userToUserDto(USER);
        userService.updateUser(MODIF_USER_DTO, 1L);

    }
}