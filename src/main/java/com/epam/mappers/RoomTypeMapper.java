package com.epam.mappers;

import com.epam.dto.RoomTypeDTO;
import com.epam.model.RoomType;

import java.util.List;

public interface RoomTypeMapper {
    RoomTypeDTO typeToTypeDTO(RoomType type);

    List<RoomTypeDTO> typesToTypesDTO(List<RoomType> types);

    RoomType typeDTOToType(RoomTypeDTO type);

    List<RoomType> typeDTOsToTypes(RoomTypeDTO types);
}
