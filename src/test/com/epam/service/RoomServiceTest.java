package com.epam.service;

import com.epam.dto.RoomDTO;
import com.epam.mappers.RoomMapper;
import com.epam.model.Room;
import com.epam.repository.RoomRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class RoomServiceTest {
    private RoomService roomService;
    private RoomMapper mockRoomMapper;
    private RoomRepository mockRoomRepository;

    @Before
    public void setup() {
        mockRoomRepository = mock(RoomRepository.class);
        mockRoomMapper = mock(RoomMapper.class);
        roomService = new RoomService(mockRoomRepository, mockRoomMapper);
    }

    @Test
    public void delete() {
        Room room = InitialVariables.someRoom();
        doReturn(room).when(mockRoomRepository).findOne(room.getId());
        roomService.deleteRoomById(room.getId());
    }

    @Test
    public void find() {
        Room room = InitialVariables.someRoom();
        RoomDTO roomDTO = InitialVariables.someRoomDTO();
        doReturn(Optional.of(room)).when(mockRoomRepository).findById(room.getId());
        doReturn(roomDTO).when(mockRoomMapper).roomToRoomDTO(room);
        roomService.findOne(room.getId());
    }

    @Test
    public void save() {
        RoomDTO roomDTO = InitialVariables.someRoomDTO();
        Room room = InitialVariables.someRoom();
        doReturn(room).when(mockRoomMapper).roomDTOToRoom(roomDTO);
        roomService.save(roomDTO);
    }
}
