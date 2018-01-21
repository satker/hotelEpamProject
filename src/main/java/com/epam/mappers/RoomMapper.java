package com.epam.mappers;

import com.epam.dto.RoomDTO;
import com.epam.model.Room;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    RoomDTO roomToRoomDTO(Room room);

    List<RoomDTO> roomsToRoomDTOs(List<Room> rooms);

    Room roomDTOToRoom(RoomDTO roomDTO);

    List<Room> roomDTOsToRooms(List<RoomDTO> roomDTO);
}
