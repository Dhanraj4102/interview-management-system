package interview_management_system.service;

import interview_management_system.dto.LoginRequest;
import interview_management_system.dto.LoginResponse;
import interview_management_system.dto.RegisterRequest;
import interview_management_system.entity.User;
import interview_management_system.exception.ResourceAlreadyExistsException;
import interview_management_system.exception.ResourceNotFoundException;
import interview_management_system.repository.UserRepository;
import interview_management_system.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String registerUser(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResourceAlreadyExistsException("Email already registered");
        }

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new RuntimeException("Passwords do not match");
        }

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("CANDIDATE")
                .createdAt(LocalDateTime.now())
                .build();

        userRepository.save(user);

        return "Registration Successful";
    }

    public LoginResponse loginUser(LoginRequest request) {

        Optional<User> userOptional =
                userRepository.findByEmail(request.getEmail());

        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }

        User user = userOptional.get();

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(
                user.getEmail(),
                user.getRole()
        );

        return LoginResponse.builder()
                .message("Login Successful")
                .token(token)
                .role(user.getRole())
                .build();
    }
}