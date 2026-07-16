package interview_management_system.service;

import interview_management_system.dto.CandidateProfileRequest;
import interview_management_system.dto.CandidateResponse;
import interview_management_system.dto.UpdateCandidateProfileRequest;
import interview_management_system.entity.Candidate;
import interview_management_system.entity.User;
import interview_management_system.exception.ResourceAlreadyExistsException;
import interview_management_system.exception.ResourceNotFoundException;
import interview_management_system.repository.CandidateRepository;
import interview_management_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CandidateService {

    private final CandidateRepository candidateRepository;
    private final UserRepository userRepository;

    public String createProfile(
            String authenticatedEmail,
            CandidateProfileRequest request) {

        User user = userRepository.findByEmail(authenticatedEmail)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        if (candidateRepository.existsByUser(user)) {
            throw new ResourceAlreadyExistsException(
                    "Candidate profile already exists"
            );
        }

        Candidate candidate = Candidate.builder()
                .user(user)
                .phone(request.getPhone())
                .experienceYears(request.getExperienceYears())
                .skills(request.getSkills())
                .employmentStatus(request.getEmploymentStatus())
                .currentLocation(request.getCurrentLocation())
                .linkedinUrl(request.getLinkedinUrl())
                .resumeUrl(request.getResumeUrl())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        candidateRepository.save(candidate);

        return "Candidate Profile Created Successfully";
    }

    public CandidateResponse getProfile(String authenticatedEmail) {

        User user = userRepository.findByEmail(authenticatedEmail)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Candidate candidate = candidateRepository.findByUser(user)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Candidate profile not found"
                        ));

        return CandidateResponse.builder()
                .id(candidate.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phone(candidate.getPhone())
                .experienceYears(candidate.getExperienceYears())
                .skills(candidate.getSkills())
                .employmentStatus(candidate.getEmploymentStatus())
                .currentLocation(candidate.getCurrentLocation())
                .linkedinUrl(candidate.getLinkedinUrl())
                .resumeUrl(candidate.getResumeUrl())
                .createdAt(candidate.getCreatedAt())
                .updatedAt(candidate.getUpdatedAt())
                .build();
    }

    public String updateProfile(
            String authenticatedEmail,
            UpdateCandidateProfileRequest request) {

        User user = userRepository.findByEmail(authenticatedEmail)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Candidate candidate = candidateRepository.findByUser(user)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Candidate profile not found"
                        ));

        candidate.setPhone(request.getPhone());
        candidate.setExperienceYears(request.getExperienceYears());
        candidate.setSkills(request.getSkills());
        candidate.setEmploymentStatus(request.getEmploymentStatus());
        candidate.setCurrentLocation(request.getCurrentLocation());
        candidate.setLinkedinUrl(request.getLinkedinUrl());
        candidate.setResumeUrl(request.getResumeUrl());
        candidate.setUpdatedAt(LocalDateTime.now());

        candidateRepository.save(candidate);

        return "Candidate Profile Updated Successfully";
    }
}