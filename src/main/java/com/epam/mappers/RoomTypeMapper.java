package com.epam.mappers;

import com.epam.dto.RoomTypeDTO;
import com.epam.model.RoomType;
import com.epam.repository.RoomTypeRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class RoomTypeMapper {
    @Autowired
    RoomTypeRepository roomTypeRepository;

    public abstract RoomTypeDTO typeToTypeDTO(RoomType type);
    @Mappings({
            @Mapping(target = "id",
                    expression = "java(roomTypeRepository.findIdByName(type.getName()))")
    })
    public abstract RoomType typeDTOToType(RoomTypeDTO type);
}
