package com.epam.controller;

import com.epam.dto.RoomConfirmDTO;
import com.epam.dto.UserDTO;
import com.epam.service.RoomConfirmService;
import com.epam.service.UserService;
import com.ge.predix.web.cors.CORSFilter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static com.epam.InitialVariables.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RoomConfirmRestControllerTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    private MockMvc mockMvc;
    @Mock
    private RoomConfirmService roomConfirmService;
    @Mock
    private UserService userService;


    @Before
    public void init() {
        RoomConfirmRestController mockRoomConfirmRestController =
                new RoomConfirmRestController(roomConfirmService, userService);
        mockMvc = MockMvcBuilders
                .standaloneSetup(mockRoomConfirmRestController)
                .addFilters(new CORSFilter())
                .build();
    }

    @Test
    public void getListConfirmsBySingleUserByIdUser() throws Exception {
        UserDTO userDTO = someUserDTO();
        List<RoomConfirmDTO> roomConfirmServices = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            roomConfirmServices.add(someRoomConfirmDTO());
        }

        doReturn(roomConfirmServices).when(roomConfirmService).findByAccountUsername(userDTO.getId());

        mockMvc.perform(get("/user/{userId}/confirms", userDTO.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[1].user.id", is(roomConfirmServices.get(1).getUser().getId())))
                .andExpect(jsonPath("$.[1].user.login", is(roomConfirmServices.get(1).getUser().getLogin())))
                .andExpect(jsonPath("$.[9].user.id", is(roomConfirmServices.get(9).getUser().getId())))
                .andExpect(jsonPath("$.[9].user.login", is(roomConfirmServices.get(9).getUser().getLogin())));

        verify(roomConfirmService).findByAccountUsername(userDTO.getId());

        verifyNoMoreInteractions(roomConfirmService);
    }

    @Test
    public void getConfirmBySingleUserByIdUser() throws Exception {
        RoomConfirmDTO roomConfirmDTO = someRoomConfirmDTO();
        Long id = someLong();

        doReturn(roomConfirmDTO).when(roomConfirmService).findOne(id);

        mockMvc.perform(get("/user/1/confirms/{confirmsId}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.user.login", is(roomConfirmDTO.getUser().getLogin())))
                .andExpect(jsonPath("$.room.number", is(roomConfirmDTO.getRoom().getNumber())));

        verify(roomConfirmService).findOne(id);

        verifyNoMoreInteractions(roomConfirmService);
    }

}
