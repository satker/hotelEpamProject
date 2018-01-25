package com.epam.service;

import com.epam.dto.RoomRequestDTO;
import com.epam.dto.UserDTO;
import com.epam.exceptions.AccessDeniedException;
import com.epam.exceptions.RoomRequestNotFoundException;
import com.epam.mappers.RoomRequestMapper;
import com.epam.mappers.UserMapper;
import com.epam.model.RoomRequest;
import com.epam.model.User;
import com.epam.repository.RoomRequestRepository;
import com.epam.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class RoomRequestServiceTest {
    private RoomRequestService roomRequestService;
    private RoomRequestMapper mockRoomRequestMapper;
    private RoomRequestRepository mockRoomRequestRepository;
    private UserService mockUserService;

    @Before
    public void setup() {

        mockUserService = mock(UserService.class);
        mockRoomRequestRepository = mock(RoomRequestRepository.class);
        mockRoomRequestMapper = mock(RoomRequestMapper.class);
        roomRequestService = new RoomRequestService(mockRoomRequestRepository, mockRoomRequestMapper, mockUserService);
    }

    @Test
    public void findValidateRoom() {
        UserDTO userDTO = InitialVariables.someUserDTO();
        RoomRequest roomRequest = InitialVariables.someRoomRequest();
        RoomRequestDTO roomRequestDTO = InitialVariables.someRoomRequestDTO();
        Collection<RoomRequest> requests = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            requests.add(InitialVariables.someRoomRequest());
        }
        doReturn(userDTO).when(mockUserService).findUserByLogin(userDTO.getLogin());
        doReturn(requests).when(mockRoomRequestRepository).findByUserId(userDTO.getId());
        doReturn(roomRequestDTO).when(mockRoomRequestMapper).requestToRequestDTO(roomRequest);
        doReturn(Optional.of(roomRequest)).when(mockRoomRequestRepository).findById(roomRequest.getId());
        roomRequestService.findValidateRoom(userDTO.getId(), userDTO.getLogin());
    }

    @Test
    public void delete() {

    }

    @Test
    public void find() {
    }

    @Test
    public void save() {

    }
}
