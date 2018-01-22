package com.epam.repository;

import com.epam.model.RoomConfirm;
import com.epam.model.RoomRequest;
import com.epam.model.User;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String email);
    Optional<User> findById(long id);
}
