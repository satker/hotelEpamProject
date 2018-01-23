package com.epam.service;

import com.epam.dto.RoomRequestDTO;
import com.epam.exceptions.AccessDeniedException;
import com.epam.exceptions.RoomRequestNotFoundException;
import com.epam.mappers.RoomRequestMapper;
import com.epam.repository.RoomRequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoomRequestService {
    private final RoomRequestRepository roomRequestRepository;
    private final RoomRequestMapper roomRequestMapper;
    private final UserService userService;

    public long getId(RoomRequestDTO roomRequest) {
        return roomRequestMapper.requestDTOToRequest(roomRequest).getId();
    }

    public List<RoomRequestDTO> findByAccountUsername(long id) {
        return roomRequestRepository.
                findByUserId(id).
                stream().
                map(roomRequestMapper::requestToRequestDTO).collect(Collectors.toList());
    }

    public RoomRequestDTO findValidateRoom(long id, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        long userId = userService.findUserByLogin(principal.getName()).getId();
        if (findByAccountUsername(userId).contains(roomRequestMapper.requestToRequestDTO(roomRequestRepository.findById(id).get()))) {
            return roomRequestMapper.requestToRequestDTO(roomRequestRepository.findById(id).orElseThrow(() -> new RoomRequestNotFoundException(id)));
        } else throw new AccessDeniedException(userId);
    }

    public void save(RoomRequestDTO request) {
        roomRequestRepository.save(roomRequestMapper.requestDTOToRequest(request));
    }

    public void deleteRoomRequestById(long id) {
        roomRequestRepository.delete(roomRequestRepository.findOne(id));
    }
}
