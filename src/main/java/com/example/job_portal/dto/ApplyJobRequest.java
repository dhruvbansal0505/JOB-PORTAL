package com.example.job_portal.dto;

public class ApplyJobRequest {
    private Long jobId;
    private String coverNote;

    public Long getJobId() { return jobId; }
    public void setJobId(Long jobId) { this.jobId = jobId; }

    public String getCoverNote() { return coverNote; }
    public void setCoverNote(String coverNote) { this.coverNote = coverNote; }
}
