package com.epam.repository;

import com.epam.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Collection<Room> findByRoomTypeId(long id);

    Optional<Room> findById(long id);
}
