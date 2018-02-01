package com.epam.controller;

import com.epam.dto.AddRoomRequestDTO;
import com.epam.dto.RoomRequestDTO;
import com.epam.dto.UserDTO;
import com.epam.model.User;
import com.epam.repository.RoomTypeRepository;
import com.epam.service.RoomRequestService;
import com.epam.service.RoomTypeService;
import com.epam.service.UserService;
import com.ge.predix.web.cors.CORSFilter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.epam.ConvertObjectToJSON.asJsonString;
import static com.epam.InitialVariables.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RoomRequestRestControllerTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    private MockMvc mockMvc;
    @Mock
    private RoomRequestService roomRequestService;
    @Mock
    private RoomTypeService mockRoomTypeService;
    @Mock
    private UserService userService;

    private static final String DEFAULT_USERNAME = "user";

    @Before
    public void init() {
        RoomRequestRestController mockRoomRequestRestController =
                new RoomRequestRestController(roomRequestService, mockRoomTypeService, userService);
        mockMvc = MockMvcBuilders
                .standaloneSetup(mockRoomRequestRestController)
                .addFilters(new CORSFilter())
                .build();
    }

    @Test
    public void getRequestByOrderId() throws Exception {
        RoomRequestDTO requestDTO = someRoomRequestDTO();
        UserDTO userDTO = someUserDTO();
        Long id = userDTO.getId();

        doReturn(requestDTO).when(roomRequestService).findValidateRoom(id, DEFAULT_USERNAME);

        mockMvc.perform(get("/user/1/orders/{orderId}", id)
                .principal(new TestingAuthenticationToken(DEFAULT_USERNAME, null)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.arrivalDate", is(requestDTO.getArrivalDate().toString())))
                .andExpect(jsonPath("$.departureDate", is(requestDTO.getDepartureDate().toString())))
                .andExpect(jsonPath("$.roomType.name", is(requestDTO.getRoomType().getName())));

        verify(roomRequestService).findValidateRoom(id, DEFAULT_USERNAME);

        verifyNoMoreInteractions(roomRequestService);
    }

    @Test
    public void getRequestsByUser() throws Exception {
        UserDTO userDTO = someUserDTO();
        List<RoomRequestDTO> requests = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            requests.add(someRoomRequestDTO());
        }

        doReturn(requests).when(roomRequestService).findByAccountUsername(userDTO.getId());

        mockMvc.perform(get("/user/{userId}/orders", userDTO.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[1].roomType.name", is(requests.get(1).getRoomType().getName())))
                .andExpect(jsonPath("$.[1].idDone", is(requests.get(1).isIdDone())))
                .andExpect(jsonPath("$.[1].arrivalDate", is(requests.get(1).getArrivalDate().toString())));

        verify(roomRequestService).findByAccountUsername(userDTO.getId());

        verifyNoMoreInteractions(roomRequestService);
    }

    @Test
    public void deleteRequestById() throws Exception {
        Long id = someLong();
        doNothing().when(roomRequestService).deleteRoomRequestById(id);
        mockMvc.perform(
                delete("/user/1/orders/{orderId}", id))
                .andExpect(status().isOk());
        verify(roomRequestService, times(1)).deleteRoomRequestById(id);
        verifyNoMoreInteractions(roomRequestService);
    }

    @Test
    public void addRequestWithSuccess() throws Exception {
        User user = someUser();
        AddRoomRequestDTO input = someAddRoomRequestDTO();

        doNothing().when(roomRequestService).save(input);
        doReturn(Optional.of(user)).when(userService).findUserById(user.getId());

        mockMvc.perform(
                post("/user/{userId}/orders", user.getId(), input)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(input)))
                .andExpect(status().isCreated());

        verify(userService).findUserById(user.getId());

        verifyNoMoreInteractions(userService);
    }
}
