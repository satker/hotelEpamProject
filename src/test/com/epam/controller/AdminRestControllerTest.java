package com.epam.controller;

import com.epam.dto.RoomConfirmDTO;
import com.epam.dto.UserDTO;
import com.epam.service.RoomConfirmService;
import com.epam.service.UserService;
import com.ge.predix.web.cors.CORSFilter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static com.epam.service.InitialVariables.someRoomConfirmDTO;
import static com.epam.service.InitialVariables.someUserDTO;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AdminRestControllerTest {
    private MockMvc mockMvc;
    @Mock
    private UserService userService;
    @Mock
    private RoomConfirmService roomConfirmService;
    @Mock
    private Principal principal;

    @Spy
    @InjectMocks
    private AdminRestController mockAdminRestController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(mockAdminRestController)
                .addFilters(new CORSFilter())
                .build();
    }

    //////////////////// Admins
    @Test
    public void getUserById() throws Exception {
        UserDTO userDTO = someUserDTO();
        Long id = userDTO.getId();
        String defaultName = userDTO.getLogin();
        when(principal.getName()).thenReturn(defaultName);
        when(userService.getUserValidateUser(id, defaultName)).thenReturn(userDTO);
        mockMvc.perform(get("/admin/{idAdmin}", id, principal))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(id)))
                .andExpect(jsonPath("$.username", is(userDTO.getLogin())));
        verify(userService, times(1)).getUserValidateUser(id, defaultName);
        verifyNoMoreInteractions(userService);
    }

    ///////////////////// Users
    @Test
    public void getUser() throws Exception {
        UserDTO userDTO = someUserDTO();

        doReturn(userDTO).when(userService).findOne(userDTO.getId());

        mockMvc.perform(get("/admin/users/{id}", userDTO.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(userDTO.getId())))
                .andExpect(jsonPath("$.login", is(userDTO.getLogin())));

        verify(userService).findOne(userDTO.getId());

        verifyNoMoreInteractions(userService);
    }

    ///////////////////// Confirms
    @Test
    public void getConfirmsByIdUser() throws Exception {
        UserDTO userDTO = someUserDTO();
        RoomConfirmDTO input = someRoomConfirmDTO();
        List<RoomConfirmDTO> roomConfirmServices = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            roomConfirmServices.add(someRoomConfirmDTO());
        }

        doReturn(roomConfirmServices).when(roomConfirmService).findByAccountUsername(userDTO.getId());

        mockMvc.perform(get("/admin/{idAdmin}/users/{id}/confirms", userDTO.getId(), userDTO.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[1].user.id", is(roomConfirmServices.get(1).getUser().getId())))
                .andExpect(jsonPath("$.[1].user.login", is(roomConfirmServices.get(1).getUser().getLogin())))
                .andExpect(jsonPath("$.[9].user.id", is(roomConfirmServices.get(9).getUser().getId())))
                .andExpect(jsonPath("$.[9].user.login", is(roomConfirmServices.get(9).getUser().getLogin())));

        verify(roomConfirmService).findByAccountUsername(userDTO.getId());

        verifyNoMoreInteractions(roomConfirmService);
    }
}
