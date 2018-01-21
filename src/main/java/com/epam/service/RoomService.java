package com.epam.service;

import com.epam.mappers.RoomMapper;
import com.epam.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
}
