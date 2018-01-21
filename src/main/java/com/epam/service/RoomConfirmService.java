package com.epam.service;

import com.epam.dto.RoomConfirmDTO;
import com.epam.mappers.RoomConfirmMapper;
import com.epam.model.RoomConfirm;
import com.epam.repository.RoomConfirmRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoomConfirmService {
    private final RoomConfirmMapper roomConfirmMapper;
    private final RoomConfirmRepository roomConfirmRepository;

    public List<RoomConfirmDTO> findByAccountUsername(long id) {
        return roomConfirmRepository.
                findByUserId(id).
                stream().
                map(roomConfirmMapper::confirmToConfirmDTO).collect(Collectors.toList());
    }

    public void save(RoomConfirmDTO confirmDTO) {
        RoomConfirm roomRequest = roomConfirmMapper.confirmDTOToConfirm(confirmDTO);
        roomConfirmRepository.save(roomRequest);
    }
    public RoomConfirmDTO findOne(long id) {
        return roomConfirmMapper.confirmToConfirmDTO(roomConfirmRepository.findOne(id));
    }
}
