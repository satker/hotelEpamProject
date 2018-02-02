package com.epam.repository;

import com.epam.model.RoomRequest;
import com.epam.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface RoomRequestRepository extends JpaRepository<RoomRequest, Long> {
    Collection<RoomRequest> findByUserId(long id);
    Optional<RoomRequest> findById(Long id);

}
