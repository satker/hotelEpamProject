package com.epam.repository;

import com.epam.model.RoomConfirm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface RoomConfirmRepository extends JpaRepository<RoomConfirm, Long> {
    Collection<RoomConfirm> findByUserId(long id);
}

