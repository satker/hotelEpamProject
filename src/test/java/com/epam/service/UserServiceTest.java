package com.epam.service;

import com.epam.dto.AddUserDTO;
import com.epam.dto.UserDTO;
import com.epam.mappers.UserMapper;
import com.epam.model.User;
import com.epam.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class UserServiceTest {
    private UserService userService;
    private UserRepository mockUserRepository;
    private UserMapper mockUserMapper;

    @Before
    public void setup() {
        mockUserRepository = mock(UserRepository.class);
        mockUserMapper = mock(UserMapper.class);
        userService = new UserService(mockUserRepository, mockUserMapper);
    }

    @Test
    public void saveUser() {
        AddUserDTO addUserDTO = new AddUserDTO();
        verify(addUserDTO).setLogin("jakson____den");
        addUserDTO.setLastName("Kunats");
        addUserDTO.setFirstName("Artem");
        addUserDTO.setPassword("1111");
        System.out.println(addUserDTO);
        User user = new User();
        doReturn(user).when(mockUserMapper).addUserDtoToUser(addUserDTO);
        doReturn(user).when(mockUserRepository).save(user);
        UserDTO actual = userService.saveUser(addUserDTO);

    }
}

