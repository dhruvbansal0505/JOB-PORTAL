package com.example.job_portal.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "job_applications")
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // candidate username (for simplicity)
    private String candidateUsername;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    // Application status
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status = ApplicationStatus.APPLIED;

    private Instant appliedAt = Instant.now();

    // optional cover note
    @Column(length = 2000)
    private String coverNote;

    public JobApplication() {
    }

    // getters / setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCandidateUsername() {
        return candidateUsername;
    }

    public void setCandidateUsername(String candidateUsername) {
        this.candidateUsername = candidateUsername;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public Instant getAppliedAt() {
        return appliedAt;
    }

    public void setAppliedAt(Instant appliedAt) {
        this.appliedAt = appliedAt;
    }

    public String getCoverNote() {
        return coverNote;
    }

    public void setCoverNote(String coverNote) {
        this.coverNote = coverNote;
    }

    public enum ApplicationStatus {
        APPLIED,
        SHORTLISTED,
        REJECTED,
        HIRED
    }
}
