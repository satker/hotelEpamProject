package com.epam.service;

import com.epam.dto.RoomRequestDTO;
import com.epam.dto.UserDTO;
import com.epam.mappers.RoomRequestMapper;
import com.epam.model.RoomRequest;
import com.epam.repository.RoomRequestRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class RoomRequestServiceTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    @Mock
    private RoomRequestMapper mockRoomRequestMapper;
    @Mock
    private RoomRequestRepository mockRoomRequestRepository;
    @Mock
    private UserService mockUserService;
    private RoomRequestService roomRequestService;

    @Before
    public void setup() {
        roomRequestService = new RoomRequestService(mockRoomRequestRepository, mockRoomRequestMapper, mockUserService);
    }

    @Test
    public void findValidateRoom() {
        UserDTO userDTO = InitialVariables.someUserDTO();
        RoomRequest roomRequest = InitialVariables.someRoomRequest();
        RoomRequestDTO roomRequestDTO = InitialVariables.someRoomRequestDTO();
        List<RoomRequestDTO> requests = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            requests.add(InitialVariables.someRoomRequestDTO());
        }

//        doReturn(userDTO).when(mockUserService).findUserByLogin(userDTO.getLogin());
//        when(roomRequestService.findByAccountUsername(userDTO.getId())).thenReturn(requests);
//        doReturn(Optional.of(roomRequest)).when(mockRoomRequestRepository).findById(roomRequest.getId());
//        doReturn(roomRequestDTO).when(mockRoomRequestMapper).requestToRequestDTO(roomRequest);
//
//        roomRequestService.findValidateRoom(userDTO.getId(), userDTO.getLogin());
    }

    @Test
    public void deleteRoomRequestById() {
        RoomRequest roomRequest = InitialVariables.someRoomRequest();
        UserDTO userDTO = InitialVariables.someUserDTO();

        doReturn(roomRequest).when(mockRoomRequestRepository).findOne(userDTO.getId());

        roomRequestService.deleteRoomRequestById(userDTO.getId());

        verify(mockRoomRequestRepository).findOne(userDTO.getId());
        verify(mockRoomRequestRepository).delete(roomRequest);

        verifyNoMoreInteractions(mockRoomRequestRepository);
    }

    @Test
    public void findOneRoomRequestById() {
        RoomRequest roomRequest = InitialVariables.someRoomRequest();
        RoomRequestDTO roomRequestDTO = InitialVariables.someRoomRequestDTO();

        doReturn(roomRequest).when(mockRoomRequestRepository).findOne(roomRequest.getId());
        doReturn(roomRequestDTO).when(mockRoomRequestMapper).requestToRequestDTO(roomRequest);

        roomRequestService.findOne(roomRequest.getId());

        verify(mockRoomRequestRepository).findOne(roomRequest.getId());
        verify(mockRoomRequestMapper).requestToRequestDTO(roomRequest);

        verifyNoMoreInteractions(mockRoomRequestRepository, mockRoomRequestMapper);
    }

    @Test
    public void saveRoomRequest() {
        RoomRequest roomRequest = InitialVariables.someRoomRequest();
        RoomRequestDTO roomRequestDTO = InitialVariables.someRoomRequestDTO();

        doReturn(roomRequest).when(mockRoomRequestRepository).findOne(roomRequest.getId());
        doReturn(roomRequestDTO).when(mockRoomRequestMapper).requestToRequestDTO(roomRequest);

        roomRequestService.save(roomRequestDTO);

        verify(mockRoomRequestMapper).requestToRequestDTO(roomRequest);
        verify(mockRoomRequestRepository).findOne(roomRequest.getId());

        verifyNoMoreInteractions(mockRoomRequestMapper, mockRoomRequestRepository);

    }
}
