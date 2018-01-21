package com.epam.service;

import com.epam.dto.RoomConfirmDTO;
import com.epam.mappers.RoomConfirmMapper;
import com.epam.model.RoomConfirm;
import com.epam.repository.RoomConfirmRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoomConfirmService {
    private final RoomConfirmMapper roomConfirmMapper;
    private final RoomConfirmRepository roomConfirmRepository;

    public void save(RoomConfirmDTO confirmDTO) {
        RoomConfirm roomRequest = roomConfirmMapper.confirmDTOToConfirm(confirmDTO);
        roomConfirmRepository.save(roomRequest);
    }
}
