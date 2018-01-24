package com.epam.service;

import com.epam.dto.RoomDTO;
import com.epam.exceptions.RoomNotFoundException;
import com.epam.mappers.RoomMapper;
import com.epam.model.Room;
import com.epam.repository.RoomRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    public List<RoomDTO> findRoomsByType(long id) {
        log.debug("all rooms have been found by room type id{}", id);
        return roomRepository.
                findByRoomTypeId(id).
                stream().
                map(roomMapper::roomToRoomDTO).collect(Collectors.toList());
    }

    public void deleteRoomById(long id) {
        log.debug("room has been deleted by id {}", id);
        roomRepository.delete(roomRepository.findOne(id));
    }

    public RoomDTO findOne(long id) {
        log.debug("room has been found by id {}", id);
        return roomMapper.roomToRoomDTO(roomRepository.findById(id).orElseThrow(() -> new RoomNotFoundException(id)));
    }

    public void save(RoomDTO request) {
        log.debug("room has been saved {}", request);
        roomRepository.save(roomMapper.roomDTOToRoom(request));
    }

}
