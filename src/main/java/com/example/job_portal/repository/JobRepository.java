package com.example.job_portal.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.job_portal.entity.Job;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByPostedBy(String postedBy);
}
