package com.epam.service;

import com.epam.dto.RoomConfirmDTO;
import com.epam.mappers.RoomConfirmMapper;
import com.epam.model.RoomConfirm;
import com.epam.repository.RoomConfirmRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class RoomConfirmService {
    private final RoomConfirmMapper roomConfirmMapper;
    private final RoomConfirmRepository roomConfirmRepository;

    public List<RoomConfirmDTO> findByAccountUsername(long id) {
        log.debug("room confims have been found by user id {}", id);
        return roomConfirmRepository.
                findByUserId(id).
                stream().
                map(roomConfirmMapper::confirmToConfirmDTO).collect(Collectors.toList());
    }

    public void save(RoomConfirmDTO confirmDTO) {
        log.debug("room confims has been saved {}", confirmDTO);
        roomConfirmRepository.save(roomConfirmMapper.confirmDTOToConfirm(confirmDTO));
    }

    public RoomConfirmDTO findOne(long id) {
        log.debug("room confirm has been found by id {}", id);
        return roomConfirmMapper.confirmToConfirmDTO(roomConfirmRepository.findOne(id));
    }

    public void deleteConfirmById(long id) {
        log.debug("confirm has been deleted by id {}", id);
        roomConfirmRepository.delete(roomConfirmRepository.findOne(id));
    }
}
