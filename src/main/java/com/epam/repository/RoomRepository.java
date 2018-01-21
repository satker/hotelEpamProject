package com.epam.repository;

import com.epam.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface RoomRepository extends JpaRepository<Room,Long> {
}
