package com.example.job_portal.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.job_portal.entity.JobApplication;
import com.example.job_portal.entity.Job;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findByCandidateUsername(String candidateUsername);

    List<JobApplication> findByJob(Job job);
}
