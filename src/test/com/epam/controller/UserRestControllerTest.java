package com.epam.controller;

import com.epam.dto.AddUserDTO;
import com.epam.dto.UserDTO;
import com.epam.service.UserService;
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

import static com.epam.ConvertObjectToJSON.asJsonString;
import static com.epam.InitialVariables.someAddUserDTO;
import static com.epam.InitialVariables.someUserDTO;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class UserRestControllerTest {
    private MockMvc mockMvc;
    @Mock
    private UserService userService;

    private static final String DEFAULT_USERNAME = "user";

    @Spy
    @InjectMocks
    private UserRestController mockUserRestController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(mockUserRestController)
                .addFilters(new CORSFilter())
                .build();
    }

    @Test
    public void getValidateUserByPrinciple() throws Exception {
        UserDTO userDTO = someUserDTO();

        when(userService.findUserByLogin(DEFAULT_USERNAME)).thenReturn(userDTO);

        mockMvc.perform(get("/user")
                .principal(new TestingAuthenticationToken(DEFAULT_USERNAME, null)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.login", is(userDTO.getLogin())))
                .andExpect(jsonPath("$.id", is(userDTO.getId())))
                .andExpect(jsonPath("$.role", is(userDTO.getRole())));

        verify(userService).findUserByLogin(DEFAULT_USERNAME);

        verifyNoMoreInteractions(userService);
    }

    @Test
    public void getValidateUserByUserIdAndPrinciple() throws Exception {
        UserDTO userDTO = someUserDTO();

        doReturn(userDTO).when(userService).getUserValidateUser(userDTO.getId(), DEFAULT_USERNAME);

        mockMvc.perform(get("/user/{id}", userDTO.getId())
                .principal(new TestingAuthenticationToken(DEFAULT_USERNAME, null)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.login", is(userDTO.getLogin())))
                .andExpect(jsonPath("$.id", is(userDTO.getId())))
                .andExpect(jsonPath("$.role", is(userDTO.getRole())));

        verify(userService).getUserValidateUser(userDTO.getId(), DEFAULT_USERNAME);

        verifyNoMoreInteractions(userService);
    }

    @Test
    public void RegistrationNewUser() throws Exception {
        AddUserDTO addUserDTO = someAddUserDTO();
        UserDTO userDTO = someUserDTO();

        doReturn(userDTO).when(userService).saveUser(addUserDTO);

        mockMvc.perform(
                post("/user", addUserDTO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(addUserDTO)))
                .andExpect(status().isCreated());

        verify(userService).saveUser(addUserDTO);

        verifyNoMoreInteractions(userService);
    }

    @Test
    public void updateUserByUserIdAndPrincipleAndNewUserDTO() throws Exception {
        UserDTO userDTO = someUserDTO();
        UserDTO modifierUser = someUserDTO();

        //doReturn("user").when(principal).getName();
        doNothing().when(userService).updateUserValidateUser(userDTO.getId(), DEFAULT_USERNAME, modifierUser);

        mockMvc.perform(put("/user/{id}", userDTO.getId(), modifierUser)
                .principal(new TestingAuthenticationToken(DEFAULT_USERNAME, null))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(modifierUser)))
                .andExpect(status().isOk());

        verify(userService).updateUserValidateUser(userDTO.getId(), DEFAULT_USERNAME, modifierUser);

        verifyNoMoreInteractions(userService);
    }
}
