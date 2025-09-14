package com.example.job_portal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.job_portal.entity.Job;
import com.example.job_portal.entity.JobApplication;
import com.example.job_portal.repository.JobApplicationRepository;

@Service
public class ApplicationService {

    private final JobApplicationRepository applicationRepository;
    private final JobService jobService;

    public ApplicationService(JobApplicationRepository applicationRepository, JobService jobService) {
        this.applicationRepository = applicationRepository;
        this.jobService = jobService;
    }

    public JobApplication apply(String candidateUsername, Long jobId, String coverNote) {
        Job job = jobService.getById(jobId).orElseThrow(() -> new IllegalArgumentException("Job not found"));
        JobApplication app = new JobApplication();
        app.setCandidateUsername(candidateUsername);
        app.setJob(job);
        app.setCoverNote(coverNote);
        return applicationRepository.save(app);
    }

    public List<JobApplication> findByCandidate(String candidateUsername) {
        return applicationRepository.findByCandidateUsername(candidateUsername);
    }

    public List<JobApplication> findByJob(Job job) {
        return applicationRepository.findByJob(job);
    }

    public Optional<JobApplication> findById(Long id) {
        return applicationRepository.findById(id);
    }

    public JobApplication update(JobApplication app) {
        return applicationRepository.save(app);
    }
}
