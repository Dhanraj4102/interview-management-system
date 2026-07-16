package interview_management_system.controller;

import interview_management_system.dto.CandidateProfileRequest;
import interview_management_system.dto.CandidateResponse;
import interview_management_system.dto.UpdateCandidateProfileRequest;
import interview_management_system.service.CandidateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;

    @PostMapping("/profile")
    public String createProfile(
            Authentication authentication,
            @Valid @RequestBody CandidateProfileRequest request) {

        return candidateService.createProfile(
                authentication.getName(),
                request
        );
    }

    @GetMapping("/profile")
    public CandidateResponse getProfile(
            Authentication authentication) {

        return candidateService.getProfile(
                authentication.getName()
        );
    }

    @PutMapping("/profile")
    public String updateProfile(
            Authentication authentication,
            @Valid @RequestBody UpdateCandidateProfileRequest request) {

        return candidateService.updateProfile(
                authentication.getName(),
                request
        );
    }
}