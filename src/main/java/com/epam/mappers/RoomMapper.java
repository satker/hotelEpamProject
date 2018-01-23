package com.epam.mappers;

import com.epam.dto.RoomDTO;
import com.epam.model.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class RoomMapper {
    @Autowired
    RoomTypeMapper roomTypeMapper;

    @Mappings({
            @Mapping(target = "roomType",
                    expression = "java(roomTypeMapper.typeToTypeDTO(room.getRoomType()))")
    })
    public abstract RoomDTO roomToRoomDTO(Room room);

    @Mappings({
            @Mapping(target = "roomType",
                    expression = "java(roomTypeMapper.typeDTOToType(roomDTO.getRoomType()))")
    })
    public abstract Room roomDTOToRoom(RoomDTO roomDTO);
}
