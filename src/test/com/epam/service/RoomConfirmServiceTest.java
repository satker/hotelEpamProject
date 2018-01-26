package com.epam.service;

import com.epam.dto.RoomConfirmDTO;
import com.epam.mappers.RoomConfirmMapper;
import com.epam.model.RoomConfirm;
import com.epam.repository.RoomConfirmRepository;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.Collection;

public class RoomConfirmServiceTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    @Mock
    private RoomConfirmMapper mockRoomConfirmMapper;
    @Mock
    private RoomConfirmRepository mockRoomConfirmRepository;

    private RoomConfirmService roomConfirmService;

    @Before
    public void setup() {
        roomConfirmService = new RoomConfirmService(mockRoomConfirmMapper, mockRoomConfirmRepository);
    }

    @Test
    public void findByAccountUsername(){
        RoomConfirm roomConfirm = InitialVariables.someRoomConfirm();
        RoomConfirmDTO roomConfirmDTO = InitialVariables.someRoomConfirmDTO();
        Collection<RoomConfirm> roomConfirms = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            roomConfirms.add(InitialVariables.someRoomConfirm());
        }
        doReturn(roomConfirmDTO).when(mockRoomConfirmMapper).confirmToConfirmDTO(roomConfirm);

    }

    @Test
    public void saveRoomConfirm(){

    }

    @Test
    public void findOneRoomConfirmById(){

    }
}
