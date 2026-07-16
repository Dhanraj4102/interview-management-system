package interview_management_system.controller;

import interview_management_system.dto.CreateInterviewRequest;
import interview_management_system.dto.DashboardResponse;
import interview_management_system.dto.InterviewResponse;
import interview_management_system.dto.UpdateInterviewRequest;
import interview_management_system.service.InterviewRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interviews")
@RequiredArgsConstructor
public class InterviewRequestController {

    private final InterviewRequestService interviewRequestService;

    @PostMapping
    public String createInterviewRequest(
            Authentication authentication,
            @Valid @RequestBody CreateInterviewRequest request) {

        return interviewRequestService.createInterviewRequest(
                authentication.getName(),
                request
        );
    }

    @GetMapping("/my")
    public List<InterviewResponse> getMyInterviewRequests(
            Authentication authentication) {

        return interviewRequestService.getInterviewRequests(
                authentication.getName()
        );
    }

    @GetMapping("/admin")
    public List<InterviewResponse> getAllInterviewRequests() {

        return interviewRequestService.getAllInterviewRequests();
    }

    @PutMapping("/{id}")
    public InterviewResponse updateInterviewRequest(
            @PathVariable Long id,
            @Valid @RequestBody UpdateInterviewRequest request) {

        return interviewRequestService.updateInterviewRequest(id, request);
    }

    @GetMapping("/search")
    public List<InterviewResponse> searchInterviews(
            @RequestParam String keyword) {

        return interviewRequestService.searchInterviews(keyword);
    }

    @GetMapping("/filter/status")
    public List<InterviewResponse> getInterviewsByStatus(
            @RequestParam String status) {

        return interviewRequestService.getInterviewsByStatus(status);
    }

    @GetMapping("/filter/result")
    public List<InterviewResponse> getInterviewsByResult(
            @RequestParam String result) {

        return interviewRequestService.getInterviewsByResult(result);
    }

    @GetMapping("/admin/dashboard")
    public DashboardResponse getDashboard() {

        return interviewRequestService.getDashboard();
    }

    @DeleteMapping("/{id}")
    public String deleteInterviewRequest(
            @PathVariable Long id) {

        return interviewRequestService.deleteInterviewRequest(id);
    }
}