package com.epam.service;

import com.epam.dto.RoomTypeDTO;
import com.epam.exceptions.RoomTypeNotFoundException;
import com.epam.mappers.RoomTypeMapper;
import com.epam.model.RoomType;
import com.epam.model.User;
import com.epam.repository.RoomTypeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class RoomTypeService {
    private final RoomTypeRepository roomTypeRepository;
    private final RoomTypeMapper roomTypeMapper;

    public List<RoomTypeDTO> findAllTypes() {
        log.debug("all room types have been found {}");
        return roomTypeRepository.
                findAll().
                stream().
                map(roomTypeMapper::typeToTypeDTO).collect(Collectors.toList());
    }

    public void deleteRoomTypeById(long id) {
        log.debug("room type by id has been deleted {}", id);
        roomTypeRepository.delete(roomTypeRepository.findOne(id));
    }

    public RoomTypeDTO findOne(long id) {
        log.debug("room type by id has been found {}", id);
        return roomTypeMapper.typeToTypeDTO(roomTypeRepository.findById(id).orElseThrow(() -> new RoomTypeNotFoundException(id)));
    }

    public void save(RoomTypeDTO request) {
        log.debug("room type has been saved {}", request);
        roomTypeRepository.save(roomTypeMapper.typeDTOToType(request));
    }
}
