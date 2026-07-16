package interview_management_system.service;

import interview_management_system.dto.CreateInterviewerRequest;
import interview_management_system.entity.Interviewer;
import interview_management_system.entity.User;
import interview_management_system.repository.InterviewerRepository;
import interview_management_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class InterviewerService {

    private final InterviewerRepository interviewerRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public String createInterviewer(CreateInterviewerRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("INTERVIEWER")
                .createdAt(LocalDateTime.now())
                .build();

        User savedUser = userRepository.save(user);

        Interviewer interviewer = Interviewer.builder()
                .user(savedUser)
                .designation(request.getDesignation())
                .specialization(request.getSpecialization())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        interviewerRepository.save(interviewer);

        return "Interviewer Created Successfully";
    }
}