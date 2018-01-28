package com.epam.service;

import com.epam.dto.RoomDTO;
import com.epam.mappers.RoomMapper;
import com.epam.model.Room;
import com.epam.repository.RoomRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Optional;

import static com.epam.InitialVariables.someRoom;
import static com.epam.InitialVariables.someRoomDTO;
import static org.mockito.Mockito.*;

public class RoomServiceTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    @Mock
    private RoomMapper mockRoomMapper;
    @Mock
    private RoomRepository mockRoomRepository;

    private RoomService roomService;

    @Before
    public void setup() {
        roomService = new RoomService(mockRoomRepository, mockRoomMapper);
    }

    @Test
    public void deleteRoomById() {
        Room room = someRoom();

        doReturn(room).when(mockRoomRepository).findOne(room.getId());

        roomService.deleteRoomById(room.getId());

        verify(mockRoomRepository).findOne(room.getId());
        verify(mockRoomRepository).delete(room);

        verifyNoMoreInteractions(mockRoomRepository);
    }

    @Test
    public void findOneRoomById() {
        Room room = someRoom();
        RoomDTO roomDTO = someRoomDTO();

        doReturn(Optional.of(room)).when(mockRoomRepository).findById(room.getId());
        doReturn(roomDTO).when(mockRoomMapper).roomToRoomDTO(room);

        roomService.findOne(room.getId());

        verify(mockRoomRepository).findById(room.getId());
        verify(mockRoomMapper).roomToRoomDTO(room);

        verifyNoMoreInteractions(mockRoomRepository, mockRoomMapper);
    }

    @Test
    public void saveRoom() {
        RoomDTO roomDTO = someRoomDTO();
        Room room = someRoom();

        doReturn(room).when(mockRoomMapper).roomDTOToRoom(roomDTO);

        roomService.save(roomDTO);

        verify(mockRoomMapper).roomDTOToRoom(roomDTO);
        verify(mockRoomRepository).save(room);

        verifyNoMoreInteractions(mockRoomMapper, mockRoomRepository);
    }
}
