package com.epam.controller;

import com.epam.dto.*;
import com.epam.model.User;
import com.epam.service.*;
import com.ge.predix.web.cors.CORSFilter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
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

public class AdminRestControllerTest {
    private MockMvc mockMvc;
    @Mock
    private UserService userService;
    @Mock
    private RoomConfirmService roomConfirmService;
    @Mock
    private RoomRequestService roomRequestService;
    @Mock
    private RoomTypeService roomTypeService;
    @Mock
    private RoomService roomService;

    private static final String DEFAULT_USERNAME = "user";

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
    public void getAdminByIdWhenAdminPresent() throws Exception {
        UserDTO userDTO = someUserDTO();
        Long id = userDTO.getId();

        when(userService.getUserValidateUser(id, DEFAULT_USERNAME)).thenReturn(userDTO);

        mockMvc.perform(get("/admin/{idAdmin}", id)
                .principal(new TestingAuthenticationToken(DEFAULT_USERNAME, null)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(id)))
                .andExpect(jsonPath("$.login", is(userDTO.getLogin())));

        verify(userService, times(1)).getUserValidateUser(id, DEFAULT_USERNAME);

        verifyNoMoreInteractions(userService);
    }

    @Test
    public void UpdatePresentAdmin() throws Exception {
        UserDTO userDTO = someUserDTO();
        Long id = userDTO.getId();

        doNothing().when(userService).updateUserValidateUser(id, DEFAULT_USERNAME, userDTO);

        mockMvc.perform(
                put("/admin/{idAdmin}", id, userDTO)
                        .principal(new TestingAuthenticationToken(DEFAULT_USERNAME, null))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userDTO)))
                .andExpect(status().isOk());

        verify(userService, times(1))
                .updateUserValidateUser(id, DEFAULT_USERNAME, userDTO);

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

    @Test
    public void deleteUserById() throws Exception {
        Long id = someLong();
        doNothing().when(userService).deleteUserById(id);
        mockMvc.perform(
                delete("/admin/users/{id}", id))
                .andExpect(status().isOk());
        verify(userService, times(1)).deleteUserById(id);
        verifyNoMoreInteractions(userService);
    }

    ///////////////////// Confirms
    @Test
    public void getConfirmById() throws Exception {
        RoomConfirmDTO roomConfirmDTO = someRoomConfirmDTO();
        Long id = someLong();

        doReturn(roomConfirmDTO).when(roomConfirmService).findOne(id);

        mockMvc.perform(get("/admin/1/users/1/confirms/{confirmId}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.user.login", is(roomConfirmDTO.getUser().getLogin())))
                .andExpect(jsonPath("$.room.number", is(roomConfirmDTO.getRoom().getNumber())));

        verify(roomConfirmService).findOne(id);

        verifyNoMoreInteractions(roomConfirmService);
    }

    @Test
    public void deleteConfirmById() throws Exception {
        Long id = someLong();
        doNothing().when(roomConfirmService).deleteConfirmById(id);
        mockMvc.perform(
                delete("/admin/1/users/1/confirms/{confirmId}", id))
                .andExpect(status().isOk());
        verify(roomConfirmService, times(1)).deleteConfirmById(id);
        verifyNoMoreInteractions(roomConfirmService);
    }

    @Test
    public void getListConfirmsByIdUser() throws Exception {
        UserDTO userDTO = someUserDTO();
        List<RoomConfirmDTO> roomConfirmServices = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            roomConfirmServices.add(someRoomConfirmDTO());
        }

        doReturn(roomConfirmServices).when(roomConfirmService).findByAccountUsername(userDTO.getId());

        mockMvc.perform(get("/admin/1/users/{id}/confirms", userDTO.getId()))
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
    public void AddUserConfirmByUserIdWithSuccess() throws Exception {
        RoomConfirmDTO input = someRoomConfirmDTO();
        User user = someUser();
        Long id = someLong();

        when(userService.findUserById(id)).thenReturn(Optional.of(user));
        doNothing().when(roomConfirmService).save(input);

        mockMvc.perform(
                post("/admin/users/{id}/confirms", id, input)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(status().isCreated());

        verify(userService, times(1)).findUserById(id);
        verifyNoMoreInteractions(userService);
    }

    //////////////////// Requests
    @Test
    public void getRequestByOrderId() throws Exception {
        RoomRequestDTO requestDTO = someRoomRequestDTO();
        Long id = someLong();

        doReturn(requestDTO).when(roomRequestService).findOne(id);

        mockMvc.perform(get("/admin/users/{id}/orders/{orderId}", id, id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.arrivalDate", is(requestDTO.getArrivalDate().toString())))
                .andExpect(jsonPath("$.departureDate", is(requestDTO.getDepartureDate().toString())))
                .andExpect(jsonPath("$.roomType.name", is(requestDTO.getRoomType().getName())));

        verify(roomRequestService).findOne(id);

        verifyNoMoreInteractions(roomRequestService);
    }

    @Test
    public void deleteRequestById() throws Exception {
        Long id = someLong();
        doNothing().when(roomRequestService).deleteRoomRequestById(id);
        mockMvc.perform(
                delete("/admin/users/1/orders/{orderId}", id, id.toString()))
                .andExpect(status().isOk());
        verify(roomRequestService, times(1)).deleteRoomRequestById(id);
        verifyNoMoreInteractions(roomRequestService);
    }

    //////////////// room type
    @Test
    public void getRoomTypeById() throws Exception {
        RoomTypeDTO typeDTO = someRoomTypeDTO();
        Long id = someLong();

        doReturn(typeDTO).when(roomTypeService).findOne(id);

        mockMvc.perform(get("/admin/appartments/{appartmentsId}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.name", is(typeDTO.getName())))
                .andExpect(jsonPath("$.description", is(typeDTO.getDescription())));

        verify(roomTypeService).findOne(id);

        verifyNoMoreInteractions(roomTypeService);
    }

    @Test
    public void deleteRoomTypeById() throws Exception {
        Long id = someLong();
        doNothing().when(roomTypeService).deleteRoomTypeById(id);
        mockMvc.perform(
                delete("/admin/appartments/{appartmentsId}", id))
                .andExpect(status().isOk());
        verify(roomTypeService, times(1)).deleteRoomTypeById(id);
        verifyNoMoreInteractions(roomTypeService);
    }

    @Test
    public void AddRoomTypeWithSuccess() throws Exception {
        RoomTypeDTO input = someRoomTypeDTO();

        doNothing().when(roomTypeService).save(input);

        mockMvc.perform(
                post("/admin/appartments", input)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(input)))
                .andExpect(status().isCreated());
        verify(roomTypeService, times(1)).save(input);
        verifyNoMoreInteractions(roomTypeService);
    }

    //////////////// Rooms
    @Test
    public void getRoomById() throws Exception {
        RoomDTO roomDTO = someRoomDTO();
        Long id = someLong();

        doReturn(roomDTO).when(roomService).findOne(id);

        mockMvc.perform(get("/admin/appartments/1/rooms/{roomsId}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.number", is(roomDTO.getNumber())))
                .andExpect(jsonPath("$.costNight", is(roomDTO.getCostNight())))
                .andExpect(jsonPath("$.roomType.name", is(roomDTO.getRoomType().getName())));

        verify(roomService).findOne(id);

        verifyNoMoreInteractions(roomService);
    }

    @Test
    public void deleteRoomById() throws Exception {
        Long id = someLong();
        doNothing().when(roomService).deleteRoomById(id);
        mockMvc.perform(
                delete("/admin/appartments/1/rooms/{roomsId}", id))
                .andExpect(status().isOk());
        verify(roomService, times(1)).deleteRoomById(id);
        verifyNoMoreInteractions(roomService);
    }

    @Test
    public void AddRoomWithSuccess() throws Exception {
        RoomDTO input = someRoomDTO();

        doNothing().when(roomService).save(input);

        mockMvc.perform(
                post("/admin/appartments/{appartmentsId}/rooms", input)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(input)))
                .andExpect(status().isCreated());
        verify(roomService, times(1)).save(input);
        verifyNoMoreInteractions(roomService);
    }
}
