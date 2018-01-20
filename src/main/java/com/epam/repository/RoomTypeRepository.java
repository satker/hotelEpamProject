package com.epam.repository;

import com.epam.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomTypeRepository extends JpaRepository<RoomType,Integer> {
    Optional<RoomType> findById(Integer id);
}
