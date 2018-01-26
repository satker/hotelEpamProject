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
        roomRequestService = spy(new RoomRequestService(mockRoomRequestRepository, mockRoomRequestMapper, mockUserService));
    }

    @Test
    public void findValidateRoom() {
        UserDTO userDTO = InitialVariables.someUserDTO();
        RoomRequest roomRequest = InitialVariables.someRoomRequest();
        List<RoomRequestDTO> requests = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            requests.add(InitialVariables.someRoomRequestDTO());
        }

        doReturn(userDTO).when(mockUserService).findUserByLogin(userDTO.getLogin());
        doReturn(requests).when(roomRequestService).findByAccountUsername(userDTO.getId());
        doReturn(Optional.of(roomRequest)).when(mockRoomRequestRepository).findById(userDTO.getId());
        doReturn(requests.get(0)).when(mockRoomRequestMapper).requestToRequestDTO(roomRequest);

        roomRequestService.findValidateRoom(userDTO.getId(), userDTO.getLogin());

        verify(mockRoomRequestRepository, times(2)).findById(userDTO.getId());
        verify(mockRoomRequestMapper, times(2)).requestToRequestDTO(roomRequest);
        verify(mockUserService).findUserByLogin(userDTO.getLogin());
        verify(roomRequestService).findByAccountUsername(userDTO.getId());

        verifyNoMoreInteractions(mockRoomRequestRepository, mockRoomRequestMapper, mockUserService);
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

        doReturn(roomRequest).when(mockRoomRequestMapper).requestDTOToRequest(roomRequestDTO);

        roomRequestService.save(roomRequestDTO);

        verify(mockRoomRequestMapper).requestDTOToRequest(roomRequestDTO);
        verify(mockRoomRequestRepository).save(roomRequest);

        verifyNoMoreInteractions(mockRoomRequestMapper, mockRoomRequestRepository);
    }
}
