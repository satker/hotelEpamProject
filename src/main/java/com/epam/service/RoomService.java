package com.epam.service;

import com.epam.dto.RoomDTO;
import com.epam.mappers.RoomMapper;
import com.epam.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    public List<RoomDTO> findAll() {
        return roomRepository.
                findAll().
                stream().
                map(roomMapper::roomToRoomDTO).collect(Collectors.toList());
    }

    public void saveRoom(RoomDTO user) {
        roomRepository.save(roomMapper.roomDTOToRoom(user));
    }

    public RoomDTO findOne(long id) {
        return roomMapper.roomToRoomDTO(roomRepository.findOne(id));
    }
}
