package com.epam.repository;

import com.epam.model.RoomRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface RoomRequestRepository extends JpaRepository<RoomRequest, Long> {
    Collection<RoomRequest> findByUserLogin(String username);
}
