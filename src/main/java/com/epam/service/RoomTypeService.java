package com.epam.service;

import com.epam.dto.RoomTypeDTO;
import com.epam.mappers.RoomTypeMapper;
import com.epam.model.RoomType;
import com.epam.model.User;
import com.epam.repository.RoomTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoomTypeService {
    private final RoomTypeRepository roomTypeRepository;
    private final RoomTypeMapper roomTypeMapper;

    public List<RoomTypeDTO> findAllTypes() {
        return roomTypeRepository.
                findAll().
                stream().
                map(roomTypeMapper::typeToTypeDTO).collect(Collectors.toList());
    }

    public void saveRoomType(RoomTypeDTO user) {
        roomTypeRepository.save(roomTypeMapper.typeDTOToType(user));
    }

    public void deleteRoomTypeById(long id) {
        roomTypeRepository.delete(roomTypeRepository.findOne(id));
    }

    public RoomTypeDTO findOne(long id) {
        return roomTypeMapper.typeToTypeDTO(roomTypeRepository.findOne(id));
    }

    public void save(RoomTypeDTO request) {
        roomTypeRepository.save(roomTypeMapper.typeDTOToType(request));
    }

    public Optional<RoomType> findRoomTypeById(long id) {
        return roomTypeRepository.findById(id);
    }
}
