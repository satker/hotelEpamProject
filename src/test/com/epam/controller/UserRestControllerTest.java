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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.security.Principal;

import static com.epam.ConvertObjectToJSON.asJsonString;
import static com.epam.InitialVariables.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class UserRestControllerTest {
    private MockMvc mockMvc;
    @Mock
    private UserService userService;
    @Mock
    private Principal principal;

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
        principal = somePrincipal();
        UserDTO userDTO = someUserDTO();

        doReturn(userDTO).when(userService).findUserByLogin(principal.getName());

        mockMvc.perform(get("/user", principal))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.login", is(userDTO.getLogin())))
                .andExpect(jsonPath("$.id", is(userDTO.getId())))
                .andExpect(jsonPath("$.role", is(userDTO.getRole())));

        verify(userService).findUserByLogin(principal.getName());

        verifyNoMoreInteractions(userService);
    }

    @Test
    public void getValidateUserByUserIdAndPrinciple() throws Exception {
        principal = somePrincipal();
        UserDTO userDTO = someUserDTO();

        doReturn(userDTO).when(userService).getUserValidateUser(userDTO.getId(), principal.getName());

        mockMvc.perform(get("/user/{id}", userDTO.getId(), principal))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.login", is(userDTO.getLogin())))
                .andExpect(jsonPath("$.id", is(userDTO.getId())))
                .andExpect(jsonPath("$.role", is(userDTO.getRole())));

        verify(userService).getUserValidateUser(userDTO.getId(), principal.getName());

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
        principal = somePrincipal();
        UserDTO userDTO = someUserDTO();
        UserDTO modifierUser = someUserDTO();

        doNothing().when(userService).updateUserValidateUser(userDTO.getId(), principal.getName(), modifierUser);

        mockMvc.perform(put("/user/{id}", userDTO.getId(), principal, modifierUser)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(modifierUser)))
                .andExpect(status().isOk());

        verify(userService).getUserValidateUser(userDTO.getId(), principal.getName());

        verifyNoMoreInteractions(userService);
    }
}
