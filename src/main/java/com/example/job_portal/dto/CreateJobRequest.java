package com.example.job_portal.dto;

public class CreateJobRequest {
    private String title;
    private String description;
    private String skills; // comma separated

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }
}
