package com.example.job_portal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.job_portal.entity.User;
import com.example.job_portal.entity.UserRole;
import com.example.job_portal.repository.UserRepository;

@SpringBootApplication
public class JobPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobPortalApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			String adminEmail = "admin@jobportal.com";
			if (userRepository.findByUsername(adminEmail).isEmpty()) {
				User admin = new User();
				admin.setUsername(adminEmail);
				admin.setPassword(passwordEncoder.encode("admin123"));
				admin.setRole(UserRole.ROLE_ADMIN);
				userRepository.save(admin);
				System.out.println("Default admin created: " + adminEmail + " / admin123");
			}
		};
	}

}