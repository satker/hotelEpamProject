package com.epam.mappers;

import com.epam.dto.RoomRequestDTO;
import com.epam.model.RoomRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomRequestMapper {

    RoomRequestDTO requestToRequestDTO(RoomRequest request);

    List<RoomRequestDTO> requestToRequestDTO(List<RoomRequest> requests);


    RoomRequest requestDTOToRequest(RoomRequestDTO requestDTO);

    List<RoomRequest> requestDTOsToRequests(List<RoomRequestDTO> requestDTO);
}
