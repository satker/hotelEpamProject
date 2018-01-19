package com.epam.repository;

import com.epam.model.RoomRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRequestRepository extends JpaRepository<RoomRequest,Long> {
}
