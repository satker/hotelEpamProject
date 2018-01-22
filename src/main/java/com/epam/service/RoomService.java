package com.epam.service;

import com.epam.dto.RoomDTO;
import com.epam.mappers.RoomMapper;
import com.epam.mappers.RoomTypeMapper;
import com.epam.model.Room;
import com.epam.model.User;
import com.epam.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoomService {
    private final RoomTypeMapper roomTypeMapper;
    private final RoomTypeService roomTypeService;
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    public List<RoomDTO> findRoomsByType(long id) {
        return roomRepository.
                findByRoomTypeId(id).
                stream().
                map(roomMapper::roomToRoomDTO).collect(Collectors.toList());
    }

    public void saveRoom(RoomDTO user) {
        roomRepository.save(roomMapper.roomDTOToRoom(user));
    }

    public void deleteRoomById(long id) {
        roomRepository.delete(roomRepository.findOne(id));
    }

    public RoomDTO findOne(long id) {
        return roomMapper.roomToRoomDTO(roomRepository.findOne(id));
    }

    public void save(RoomDTO request) {
        roomRepository.save(roomMapper.roomDTOToRoom(request));
    }

    public Optional<Room> findRoomById(long id) {
        return roomRepository.findById(id);
    }
}

