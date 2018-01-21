package com.epam.mappers;

import com.epam.dto.RoomConfirmDTO;
import com.epam.model.RoomConfirm;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomConfirmMapper {
    RoomConfirmDTO requestToRequestDTO(RoomConfirm request);

    List<RoomConfirmDTO> requestToRequestDTO(List<RoomConfirm> requests);

    RoomConfirm requestDTOToRequest(RoomConfirmDTO requestDTO);

    List<RoomConfirm> requestDTOsToRequests(List<RoomConfirmDTO> requestDTO);
}
