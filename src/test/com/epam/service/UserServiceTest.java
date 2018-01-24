package com.epam.service;

import com.epam.mappers.UserMapper;
import com.epam.model.User;
import com.epam.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.Answer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static com.epam.service.InitialVariables.ADD_USER_DTO;
import static com.epam.service.InitialVariables.USER;
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
    public void saveUser() {
        doReturn(USER).when(mockUserMapper).addUserDtoToUser(ADD_USER_DTO);
        doReturn(USER).when(mockUserRepository).save(USER);
        when(mockUserRepository.save(any(User.class)))
                .thenAnswer((Answer<User>) invocation -> {
                    User user1 = (User) invocation.getArguments()[0];
                    user1.setId(1L);
                    return user1;
                });
        userService.saveUser(ADD_USER_DTO);
    }
}