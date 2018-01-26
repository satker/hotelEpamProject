package com.epam.service;

import com.epam.dto.AddUserDTO;
import com.epam.dto.UserDTO;
import com.epam.mappers.UserMapper;
import com.epam.model.User;
import com.epam.repository.UserRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class UserServiceTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private UserMapper mockUserMapper;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserService userService;

    @Before
    public void setup() {
        userService = new UserService(mockUserRepository, mockUserMapper, bCryptPasswordEncoder);
    }

    @Test
    public void saveUser() {
        UserDTO userDTO = InitialVariables.someUserDTO();
        User user = InitialVariables.someUser();
        AddUserDTO addUserDTO = InitialVariables.someAddUserDTO();

        doReturn(user).when(mockUserMapper).addUserDtoToUser(addUserDTO);
        doReturn(userDTO).when(mockUserMapper).userToUserDto(user);
        doReturn(user).when(mockUserRepository).save(user);

        userService.saveUser(addUserDTO);

        verify(mockUserMapper).addUserDtoToUser(addUserDTO);
        verify(mockUserRepository).save(user);
        verify(mockUserMapper).userToUserDto(user);

        verifyNoMoreInteractions(mockUserMapper, mockUserRepository);
    }

    @Test
    public void findOneUserById() {
        User user = InitialVariables.someUser();
        UserDTO userDTO = InitialVariables.someUserDTO();

        doReturn(userDTO).when(mockUserMapper).userToUserDto(user);
        doReturn(Optional.of(user)).when(mockUserRepository).findById(any(Long.class));

        userService.findOne(user.getId());

        verify(mockUserMapper).userToUserDto(user);
        verify(mockUserRepository).findById(any(Long.class));

        verifyNoMoreInteractions(mockUserMapper, mockUserRepository);
    }

    @Test
    public void updateUser() {
        User user = InitialVariables.someUser();
        UserDTO userDTO = InitialVariables.someUserDTO();

        doReturn(user).when(mockUserRepository).findOne(any(Long.class));
        doReturn(userDTO).when(mockUserMapper).userToUserDto(user);
        doReturn(user).when(mockUserRepository).save(user);

        userService.updateUser(userDTO, 1L);

        verify(mockUserRepository).findOne(any(Long.class));
        verify(mockUserMapper).userToUserDto(user);
        verify(mockUserRepository).save(user);

        verifyNoMoreInteractions(mockUserRepository, mockUserMapper);
    }
}
