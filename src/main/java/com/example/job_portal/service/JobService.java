package com.example.job_portal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.job_portal.entity.Job;
import com.example.job_portal.repository.JobRepository;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    public Optional<Job> getById(Long id) {
        return jobRepository.findById(id);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public List<Job> getByPoster(String postedBy) {
        return jobRepository.findByPostedBy(postedBy);
    }

    public void delete(Long id) {
        jobRepository.deleteById(id);
    }

    public Job update(Job existing) {
        return jobRepository.save(existing);
    }
}
