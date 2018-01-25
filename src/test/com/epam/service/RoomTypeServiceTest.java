package com.epam.service;

import com.epam.dto.RoomTypeDTO;
import com.epam.mappers.RoomTypeMapper;
import com.epam.model.RoomType;
import com.epam.repository.RoomTypeRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class RoomTypeServiceTest {
    private RoomTypeService roomTypeService;
    private RoomTypeMapper mockRoomTypeMapper;
    private RoomTypeRepository mockRoomTypeRepository;

    @Before
    public void setup() {
        mockRoomTypeRepository = mock(RoomTypeRepository.class);
        mockRoomTypeMapper = mock(RoomTypeMapper.class);
        roomTypeService = new RoomTypeService(mockRoomTypeRepository, mockRoomTypeMapper);
    }

    @Test
    public void delete() {
        RoomType roomType = InitialVariables.someRoomType();
        doReturn(roomType).when(mockRoomTypeRepository).findOne(roomType.getId());
        roomTypeService.deleteRoomTypeById(roomType.getId());
    }

    @Test
    public void find() {
        RoomType roomType = InitialVariables.someRoomType();
        RoomTypeDTO roomTypeDTO = InitialVariables.someRoomTypeDTO();
        doReturn(Optional.of(roomType)).when(mockRoomTypeRepository).findById(roomType.getId());
        doReturn(roomTypeDTO).when(mockRoomTypeMapper).typeToTypeDTO(roomType);
        roomTypeService.findOne(roomType.getId());
    }

    @Test
    public void save() {
        RoomTypeDTO roomTypeDTO = InitialVariables.someRoomTypeDTO();
        RoomType roomType = InitialVariables.someRoomType();
        doReturn(roomType).when(mockRoomTypeMapper).typeDTOToType(roomTypeDTO);
        roomTypeService.save(roomTypeDTO);
    }
}
