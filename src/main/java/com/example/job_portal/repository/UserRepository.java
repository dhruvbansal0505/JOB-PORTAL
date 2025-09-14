package com.example.job_portal.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.job_portal.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
