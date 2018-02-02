package com.epam.repository;

import com.epam.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {
    Optional<RoomType> findById(Long id);
    Optional<RoomType> findByName(String name);
    default Long findIdByName(String name){
        Optional<RoomType> roomType = findByName(name);
        return roomType.get().getId();
    }
}
