package com.example.job_portal.service;

import com.example.job_portal.entity.CandidateProfile;
import com.example.job_portal.entity.User;
import com.example.job_portal.repository.CandidateProfileRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

@Service
public class CandidateService {

    private final CandidateProfileRepository profileRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public CandidateService(CandidateProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    /**
     * Create or update a candidate profile
     */
    public CandidateProfile createProfile(User user, String fullName, String qualification, String skills,
            MultipartFile resume) throws IOException {
        CandidateProfile profile = profileRepository.findByUser(user).orElse(new CandidateProfile());

        profile.setUser(user);
        profile.setFullName(fullName);
        profile.setQualification(qualification);
        profile.setSkills(skills);

        // handle resume upload
        if (resume != null && !resume.isEmpty()) {
            Path uploadPath = Paths.get(uploadDir).toAbsolutePath();
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            String filename = System.currentTimeMillis() + "_" + resume.getOriginalFilename();
            Path filePath = uploadPath.resolve(filename);
            Files.copy(resume.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            profile.setResumePath(filePath.toString());
        }

        return profileRepository.save(profile);
    }

    /**
     * Find a profile by user
     */
    public CandidateProfile getProfile(User user) {
        return profileRepository.findByUser(user).orElse(null);
    }
}
