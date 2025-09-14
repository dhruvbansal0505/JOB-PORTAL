package com.example.job_portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.job_portal.entity.CandidateProfile;
import com.example.job_portal.entity.User;
import java.util.Optional;

public interface CandidateProfileRepository extends JpaRepository<CandidateProfile, Long> {
    Optional<CandidateProfile> findByUser(User user);
}
