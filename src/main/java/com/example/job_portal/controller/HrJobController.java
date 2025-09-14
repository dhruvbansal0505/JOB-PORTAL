package com.example.job_portal.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.job_portal.dto.CreateJobRequest;
import com.example.job_portal.entity.Job;
import com.example.job_portal.entity.JobApplication;
import com.example.job_portal.service.ApplicationService;
import com.example.job_portal.service.JobService;

@RestController
@RequestMapping("/api/hr")
public class HrJobController {

    private final JobService jobService;
    private final ApplicationService applicationService;

    public HrJobController(JobService jobService, ApplicationService applicationService) {
        this.jobService = jobService;
        this.applicationService = applicationService;
    }

    // HR creates a job posting
    @PostMapping("/jobs")
    public ResponseEntity<?> createJob(@RequestBody CreateJobRequest req, Principal principal) {
        Job job = new Job();
        job.setTitle(req.getTitle());
        job.setDescription(req.getDescription());
        job.setSkills(req.getSkills());
        job.setPostedBy(principal.getName()); // username from auth
        Job saved = jobService.createJob(job);
        return ResponseEntity.ok(saved);
    }

    // HR gets jobs they posted
    @GetMapping("/jobs/mine")
    public ResponseEntity<List<Job>> myJobs(Principal principal) {
        List<Job> list = jobService.getByPoster(principal.getName());
        return ResponseEntity.ok(list);
    }

    // HR can delete a job they posted
    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<?> deleteJob(@PathVariable Long id, Principal principal) {
        return jobService.getById(id).map(job -> {
            if (!principal.getName().equals(job.getPostedBy())) {
                return ResponseEntity.status(403).body("Not authorized to delete this job");
            }
            jobService.delete(id);
            return ResponseEntity.ok("Deleted");
        }).orElse(ResponseEntity.notFound().build());
    }

    // HR: view applications for a job
    @GetMapping("/jobs/{id}/applications")
    public ResponseEntity<?> viewApplications(@PathVariable Long id, Principal principal) {
        return jobService.getById(id).map(job -> {
            if (!principal.getName().equals(job.getPostedBy())) {
                return ResponseEntity.status(403).body("Not authorized to view applications");
            }
            List<JobApplication> apps = applicationService.findByJob(job);
            return ResponseEntity.ok(apps);
        }).orElse(ResponseEntity.notFound().build());
    }

    // HR: update application status
    @PostMapping("/applications/{id}/status")
    public ResponseEntity<?> updateApplicationStatus(@PathVariable Long id, @RequestParam String status, Principal principal) {
        return applicationService.findById(id).map(app -> {
            Job job = app.getJob();
            if (!principal.getName().equals(job.getPostedBy())) {
                return ResponseEntity.status(403).body("Not authorized");
            }
            try {
                JobApplication.ApplicationStatus st = JobApplication.ApplicationStatus.valueOf(status.toUpperCase());
                app.setStatus(st);
                applicationService.update(app);
                return ResponseEntity.ok(app);
            } catch (IllegalArgumentException ex) {
                return ResponseEntity.badRequest().body("Invalid status");
            }
        }).orElse(ResponseEntity.notFound().build());
    }
}
