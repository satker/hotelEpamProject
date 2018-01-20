package com.epam.service;

import com.epam.dto.RoomRequestDTO;
import com.epam.dto.RoomTypeDTO;
import com.epam.mappers.RoomRequestMapper;
import com.epam.mappers.RoomTypeMapper;
import com.epam.model.RoomRequest;
import com.epam.model.RoomType;
import com.epam.repository.RoomRequestRepository;
import com.epam.repository.RoomTypeRepository;
import com.epam.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoomRequestService {
    private final RoomRequestRepository roomRequestRepository;
    private final RoomRequestMapper roomRequestMapper;
    private final RoomTypeRepository roomTypeRepository;
    private final UserRepository userRepository;

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
        return roomRequestMapper.requestToRequestDTO(roomRequestRepository.findOne(id));
    }

    public void save(RoomRequestDTO request) {
        RoomRequest roomRequest = roomRequestMapper.requestDTOToRequest(request);
        roomRequestRepository.save(roomRequest);
    }

    public void deleteRoomRequestById(long id) {
        roomRequestRepository.delete(roomRequestRepository.findOne(id));
    }
}
