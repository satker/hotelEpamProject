package com.epam.service;

import com.epam.dto.RoomRequestDTO;
import com.epam.exceptions.RoomRequestNotFoundException;
import com.epam.mappers.RoomRequestMapper;
import com.epam.repository.RoomRequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoomRequestService {
    private final RoomRequestRepository roomRequestRepository;
    private final RoomRequestMapper roomRequestMapper;

    public long getId(RoomRequestDTO roomRequest) {
        return roomRequestMapper.requestDTOToRequest(roomRequest).getId();
    }

    public List<RoomRequestDTO> findByAccountUsername(long id) {
        return roomRequestRepository.
                findByUserId(id).
                stream().
                map(roomRequestMapper::requestToRequestDTO).collect(Collectors.toList());
    }

    public RoomRequestDTO findOne(long id) {
        return roomRequestMapper.requestToRequestDTO(roomRequestRepository.findById(id).orElseThrow(() -> new RoomRequestNotFoundException(id)));
    }

    public void save(RoomRequestDTO request) {
        roomRequestRepository.save(roomRequestMapper.requestDTOToRequest(request));
    }

    public void deleteRoomRequestById(long id) {
        roomRequestRepository.delete(roomRequestRepository.findOne(id));
    }
}
