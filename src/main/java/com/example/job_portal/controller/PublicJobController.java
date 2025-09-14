package com.example.job_portal.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.job_portal.entity.Job;
import com.example.job_portal.service.JobService;

@RestController
@RequestMapping("/api/jobs")
public class PublicJobController {

    private final JobService jobService;

    public PublicJobController(JobService jobService) {
        this.jobService = jobService;
    }

    // public: list jobs
    @GetMapping
    public ResponseEntity<List<Job>> listJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    // public: job detail
    @GetMapping("/{id}")
    public ResponseEntity<?> getJob(@PathVariable Long id) {
        return jobService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
