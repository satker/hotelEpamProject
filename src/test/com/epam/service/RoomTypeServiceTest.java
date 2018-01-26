package com.epam.service;

import com.epam.dto.RoomTypeDTO;
import com.epam.mappers.RoomTypeMapper;
import com.epam.model.RoomType;
import com.epam.repository.RoomTypeRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class RoomTypeServiceTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    @Mock
    private RoomTypeMapper mockRoomTypeMapper;
    @Mock
    private RoomTypeRepository mockRoomTypeRepository;

    private RoomTypeService roomTypeService;

    @Before
    public void setup() {
        roomTypeService = new RoomTypeService(mockRoomTypeRepository, mockRoomTypeMapper);
    }

    @Test
    public void deleteRoomTypeById() {
        RoomType roomType = InitialVariables.someRoomType();

        doReturn(roomType).when(mockRoomTypeRepository).findOne(roomType.getId());

        roomTypeService.deleteRoomTypeById(roomType.getId());

        verify(mockRoomTypeRepository).findOne(roomType.getId());
        verify(mockRoomTypeRepository).delete(roomType);

        verifyNoMoreInteractions(mockRoomTypeRepository);
    }

    @Test
    public void findOneRoomTypeById() {
        RoomType roomType = InitialVariables.someRoomType();
        RoomTypeDTO roomTypeDTO = InitialVariables.someRoomTypeDTO();

        doReturn(Optional.of(roomType)).when(mockRoomTypeRepository).findById(roomType.getId());
        doReturn(roomTypeDTO).when(mockRoomTypeMapper).typeToTypeDTO(roomType);

        roomTypeService.findOne(roomType.getId());

        verify(mockRoomTypeRepository).findById(roomType.getId());
        verify(mockRoomTypeMapper).typeToTypeDTO(roomType);

        verifyNoMoreInteractions(mockRoomTypeRepository, mockRoomTypeMapper);
    }

    @Test
    public void saveRoomType() {
        RoomTypeDTO roomTypeDTO = InitialVariables.someRoomTypeDTO();
        RoomType roomType = InitialVariables.someRoomType();

        doReturn(roomType).when(mockRoomTypeMapper).typeDTOToType(roomTypeDTO);

        roomTypeService.save(roomTypeDTO);

        verify(mockRoomTypeMapper).typeDTOToType(roomTypeDTO);
        verify(mockRoomTypeRepository).save(roomType);

        verifyNoMoreInteractions(mockRoomTypeMapper, mockRoomTypeRepository);
    }
}
