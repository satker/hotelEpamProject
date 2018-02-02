package com.epam.service;

import com.epam.model.User;
import com.epam.repository.UserRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Optional;

import static com.epam.InitialVariables.someUser;
import static org.mockito.Mockito.*;

public class MyAppUserDetailsServiceTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    @Mock
    private UserRepository mockUserRepository;

    private MyAppUserDetailsService myAppUserDetailsService;

    @Before
    public void setup() {
        myAppUserDetailsService = new MyAppUserDetailsService(mockUserRepository);
    }

    @Test
    public void loadUserByUsername() {
        User user = someUser();
        String login = user.getLogin();

        doReturn(Optional.of(user)).when(mockUserRepository).findByLogin(login);

        myAppUserDetailsService.loadUserByUsername(login);

        verify(mockUserRepository).findByLogin(login);

        verifyNoMoreInteractions(mockUserRepository);
    }
}
