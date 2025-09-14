package com.example.job_portal.controller;

import com.example.job_portal.dto.ApplyJobRequest;
import com.example.job_portal.entity.CandidateProfile;
import com.example.job_portal.entity.JobApplication;
import com.example.job_portal.entity.User;
import com.example.job_portal.repository.UserRepository;
import com.example.job_portal.service.ApplicationService;
import com.example.job_portal.service.CandidateService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/candidate")
public class CandidateController {

    private final CandidateService candidateService;
    private final UserRepository userRepository;
    private final ApplicationService applicationService;

    public CandidateController(
            CandidateService candidateService,
            UserRepository userRepository,
            ApplicationService applicationService) {
        this.candidateService = candidateService;
        this.userRepository = userRepository;
        this.applicationService = applicationService;
    }

    // 1. Create profile (after registration)
    @PostMapping("/profile")
    public ResponseEntity<?> createProfile(
            @RequestParam String fullName,
            @RequestParam String qualification,
            @RequestParam String skills,
            @RequestParam("resume") MultipartFile resume,
            Principal principal) throws IOException {

        User user = userRepository.findByUsername(principal.getName()).orElseThrow();
        CandidateProfile profile = candidateService.createProfile(user, fullName, qualification, skills, resume);
        return ResponseEntity.ok(profile);
    }

    // 2. Apply for a job
    @PostMapping("/apply")
    public ResponseEntity<?> apply(@RequestBody ApplyJobRequest req, Principal principal) {
        JobApplication app = applicationService.apply(principal.getName(), req.getJobId(), req.getCoverNote());
        return ResponseEntity.ok(app);
    }

    // 3. View my applications
    @GetMapping("/applications")
    public ResponseEntity<List<JobApplication>> myApplications(Principal principal) {
        List<JobApplication> list = applicationService.findByCandidate(principal.getName());
        return ResponseEntity.ok(list);
    }
}
