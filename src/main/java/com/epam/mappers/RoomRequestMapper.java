package com.epam.mappers;

import com.epam.dto.RoomRequestDTO;
import com.epam.model.RoomRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class RoomRequestMapper {
    @Autowired
    RoomTypeMapper roomTypeMapper;

    @Mappings({
            @Mapping(target = "roomType",
                    expression = "java(roomTypeMapper.typeToTypeDTO(request.getRoomType()))")
    })
    public abstract RoomRequestDTO requestToRequestDTO(RoomRequest request);

    @Mappings({
            @Mapping(target = "roomType",
                    expression = "java(roomTypeMapper.typeDTOToType(requestDTO.getRoomType()))")
    })
    public abstract RoomRequest requestDTOToRequest(RoomRequestDTO requestDTO);
}
