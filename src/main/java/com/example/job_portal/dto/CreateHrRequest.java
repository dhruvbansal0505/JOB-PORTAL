package com.example.job_portal.dto;

public class CreateHrRequest {
    private String username;
    private String password;
    // add other fields if needed

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
