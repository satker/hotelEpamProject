package com.epam.mappers;

import com.epam.dto.RoomConfirmDTO;
import com.epam.model.RoomConfirm;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomConfirmMapper {
    RoomConfirmDTO confirmToConfirmDTO(RoomConfirm confirm);

    List<RoomConfirmDTO> confirmsToConfirmDTOs(List<RoomConfirm> confirms);

    RoomConfirm confirmDTOToConfirm(RoomConfirmDTO confirmDTO);

    List<RoomConfirm> confirmDTOsToConfirm(List<RoomConfirmDTO> confirmDTO);
}
