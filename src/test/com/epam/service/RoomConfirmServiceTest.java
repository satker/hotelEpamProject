package com.epam.service;

import com.epam.dto.RoomConfirmDTO;
import com.epam.dto.RoomRequestDTO;
import com.epam.mappers.RoomConfirmMapper;
import com.epam.mappers.RoomRequestMapper;
import com.epam.model.RoomConfirm;
import com.epam.model.RoomRequest;
import com.epam.repository.RoomConfirmRepository;
import com.epam.repository.RoomRequestRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import static com.epam.InitialVariables.*;
import static org.mockito.Mockito.*;

public class RoomConfirmServiceTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    @Mock
    private RoomConfirmMapper mockRoomConfirmMapper;
    @Mock
    private RoomConfirmRepository mockRoomConfirmRepository;
    @Mock
    private RoomRequestService mockRoomRequestService;
    @Mock
    private RoomRequestMapper mockRoomRequestMapper;
    @Mock
    private RoomRequestRepository mockRoomRequestRepository;

    private RoomConfirmService roomConfirmService;

    @Before
    public void setup() {
        roomConfirmService = new RoomConfirmService(mockRoomConfirmMapper,
                mockRoomConfirmRepository,
                mockRoomRequestService,
                mockRoomRequestMapper,
                mockRoomRequestRepository);
    }

    @Test
    public void findRoomConfirmsByAccountUsername() {
        RoomConfirmDTO roomConfirmDTO = someRoomConfirmDTO();
        List<RoomConfirm> roomConfirms = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            roomConfirms.add(someRoomConfirm());
        }

        doReturn(roomConfirmDTO).when(mockRoomConfirmMapper).confirmToConfirmDTO(roomConfirms.get(0));
        doReturn(roomConfirms).when(mockRoomConfirmRepository).findByUserId(roomConfirms.get(0).getId());

        roomConfirmService.findByAccountUsername(roomConfirms.get(0).getId());

        verify(mockRoomConfirmMapper).confirmToConfirmDTO(roomConfirms.get(0));
        verify(mockRoomConfirmRepository).findByUserId(roomConfirms.get(0).getId());

        verifyNoMoreInteractions(mockRoomConfirmRepository);
    }

    @Test
    public void saveRoomConfirm() {
        RoomConfirm roomConfirm = someRoomConfirm();
        RoomConfirmDTO roomConfirmDTO = someRoomConfirmDTO();
        RoomRequestDTO roomRequestDTO = someRoomRequestDTO();
        RoomRequest roomRequest = someRoomRequest();

        doReturn(roomConfirm).when(mockRoomConfirmMapper).confirmDTOToConfirm(roomConfirmDTO);
        doReturn(roomRequestDTO).when(mockRoomRequestService).findOne(roomConfirmDTO.getRequest().getId());
        doReturn(roomRequest).when(mockRoomRequestMapper).requestDTOToRequest(roomRequestDTO);
        doReturn(roomRequest).when(mockRoomRequestRepository).save(roomRequest);
        doReturn(roomRequestDTO).when(mockRoomRequestMapper).requestToRequestDTO(roomRequest);

        roomConfirmService.save(roomConfirmDTO);

        verify(mockRoomConfirmMapper).confirmDTOToConfirm(roomConfirmDTO);
        verify(mockRoomConfirmRepository).save(roomConfirm);
        verify(mockRoomRequestService).findOne(roomConfirmDTO.getRequest().getId());
        verify(mockRoomRequestMapper).requestDTOToRequest(roomRequestDTO);
        verify(mockRoomRequestRepository).save(roomRequest);
        verify(mockRoomRequestMapper).requestToRequestDTO(roomRequest);


        verifyNoMoreInteractions(mockRoomConfirmRepository, mockRoomConfirmMapper,
                mockRoomRequestService, mockRoomRequestMapper, mockRoomRequestRepository);
    }

    @Test
    public void findOneRoomConfirmById() {
        RoomConfirm roomConfirm = someRoomConfirm();
        RoomConfirmDTO roomConfirmDTO = someRoomConfirmDTO();

        doReturn(roomConfirm).when(mockRoomConfirmRepository).findOne(roomConfirmDTO.getUser().getId());
        doReturn(roomConfirmDTO).when(mockRoomConfirmMapper).confirmToConfirmDTO(roomConfirm);

        roomConfirmService.findOne(roomConfirmDTO.getUser().getId());

        verify(mockRoomConfirmRepository).findOne(roomConfirmDTO.getUser().getId());
        verify(mockRoomConfirmMapper).confirmToConfirmDTO(roomConfirm);

        verifyNoMoreInteractions(mockRoomConfirmMapper, mockRoomConfirmRepository);
    }
}
