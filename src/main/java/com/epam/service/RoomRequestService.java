package com.epam.service;

import com.epam.dto.RoomRequestDTO;
import com.epam.exceptions.AccessDeniedException;
import com.epam.exceptions.RoomRequestNotFoundException;
import com.epam.mappers.RoomRequestMapper;
import com.epam.repository.RoomRequestRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class RoomRequestService {
    private final RoomRequestRepository roomRequestRepository;
    private final RoomRequestMapper roomRequestMapper;
    private final UserService userService;


    public List<RoomRequestDTO> findByAccountUsername(long id) {
        log.debug("all room requests have been found by user id {}", id);
        return roomRequestRepository.
                findByUserId(id).
                stream().
                map(roomRequestMapper::requestToRequestDTO).
                collect(Collectors.toList());
    }

    public RoomRequestDTO findValidateRoom(long id, String login) {
        log.debug("room request has been found for validate user by id {}", id);
        long userId = userService.findUserByLogin(login).getId();
        if (findByAccountUsername(userId).
                contains(roomRequestMapper.
                        requestToRequestDTO(roomRequestRepository.
                                findById(id).
                                get()))) {
            return roomRequestMapper.requestToRequestDTO(roomRequestRepository.findById(id).orElseThrow(() -> new RoomRequestNotFoundException(id)));
        } else {
            throw new AccessDeniedException(userId);
        }
    }

    public void save(RoomRequestDTO request) {
        log.debug("room request has been saved {}", request);
        roomRequestRepository.save(roomRequestMapper.requestDTOToRequest(request));
    }

    public RoomRequestDTO findOne(long id) {
        log.debug("room request has been found by id {}", id);
        return roomRequestMapper.requestToRequestDTO(roomRequestRepository.findOne(id));
    }

    public void deleteRoomRequestById(long id) {
        log.debug("room request has been deleted by id {}", id);
        roomRequestRepository.delete(roomRequestRepository.findOne(id));
    }
}
