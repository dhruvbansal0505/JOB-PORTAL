package com.example.job_portal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hr")
public class HrController {

    // example endpoint only HRs can use
    @GetMapping("/dashboard")
    public ResponseEntity<?> dashboard() {
        return ResponseEntity.ok("HR dashboard - secure");
    }
}
