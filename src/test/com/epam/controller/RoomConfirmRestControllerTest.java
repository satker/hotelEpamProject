package com.epam.controller;

import com.epam.dto.RoomConfirmDTO;
import com.epam.dto.UserDTO;
import com.epam.service.RoomConfirmService;
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

import java.util.ArrayList;
import java.util.List;

import static com.epam.InitialVariables.someLong;
import static com.epam.InitialVariables.someRoomConfirmDTO;
import static com.epam.InitialVariables.someUserDTO;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RoomConfirmRestControllerTest {
    private MockMvc mockMvc;
    @Mock
    private RoomConfirmService roomConfirmService;

    @Spy
    @InjectMocks
    private RoomConfirmRestController mockRoomConfirmRestController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
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
