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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.epam.InitialVariables.*;
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
    public void deleteAllUsers(){
        doNothing().when(mockUserRepository).deleteAll();

        userService.deleteAllUsers();

        verify(mockUserRepository).deleteAll();

        verifyNoMoreInteractions(mockUserRepository);
    }

    @Test
    public void deleteUserById(){
        Long id = someLong();

        doNothing().when(mockUserRepository).delete(id);

        userService.deleteUserById(id);

        verify(mockUserRepository).delete(id);

        verifyNoMoreInteractions(mockUserRepository);
    }

    @Test
    public void updateUser() {
        User user = someUser();
        UserDTO userDTO = someUserDTO();

        doReturn(user).when(mockUserRepository).findOne(any(Long.class));
        doReturn(userDTO).when(mockUserMapper).userToUserDto(user);
        doReturn(user).when(mockUserRepository).save(user);

        userService.updateUser(userDTO, 1L);

        verify(mockUserRepository).findOne(any(Long.class));
        verify(mockUserMapper).userToUserDto(user);
        verify(mockUserRepository).save(user);

        verifyNoMoreInteractions(mockUserRepository, mockUserMapper);
    }

    @Test
    public void findUserByLogin(){
        User user = someUser();
        UserDTO userDTO = someUserDTO();

        doReturn(Optional.of(user)).when(mockUserRepository).findByLogin(user.getLogin());
        doReturn(userDTO).when(mockUserMapper).userToUserDto(user);

        userService.findUserByLogin(user.getLogin());

        verify(mockUserRepository).findByLogin(user.getLogin());
        verify(mockUserMapper).userToUserDto(user);

        verifyNoMoreInteractions(mockUserRepository, mockUserMapper);
    }

    @Test
    public void findOneUserById() {
        User user = someUser();
        UserDTO userDTO = someUserDTO();

        doReturn(userDTO).when(mockUserMapper).userToUserDto(user);
        doReturn(Optional.of(user)).when(mockUserRepository).findById(any(Long.class));

        userService.findOne(user.getId());

        verify(mockUserMapper).userToUserDto(user);
        verify(mockUserRepository).findById(any(Long.class));

        verifyNoMoreInteractions(mockUserMapper, mockUserRepository);
    }

    @Test
    public void findOneById(){
        Long id = someLong();
        User user = someUser();

        doReturn(Optional.of(user)).when(mockUserRepository).findById(id);

        userService.findUserById(id);

        verify(mockUserRepository).findById(id);

        verifyNoMoreInteractions(mockUserRepository);
    }

    @Test
    public void findAllUsers(){
        List<User> userList = new ArrayList<>();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            userList.add(someUser());
            userDTOList.add(someUserDTO());
        }

        doReturn(userDTOList).when(mockUserMapper).usersToUsersDto(userList);
        doReturn(userList).when(mockUserRepository).findAll();

        userService.findAllUsers();

        verify(mockUserMapper).usersToUsersDto(userList);
        verify(mockUserRepository).findAll();

        verifyNoMoreInteractions(mockUserMapper, mockUserRepository);
    }

    @Test
    public void saveUser() {
        UserDTO userDTO = someUserDTO();
        User user = someUser();
        AddUserDTO addUserDTO = someAddUserDTO();

        doReturn(user).when(mockUserMapper).addUserDtoToUser(addUserDTO);
        doReturn(userDTO).when(mockUserMapper).userToUserDto(user);
        doReturn(user).when(mockUserRepository).save(user);

        userService.saveUser(addUserDTO);

        verify(mockUserMapper).addUserDtoToUser(addUserDTO);
        verify(mockUserRepository).save(user);
        verify(mockUserMapper).userToUserDto(user);

        verifyNoMoreInteractions(mockUserMapper, mockUserRepository);
    }
}
