package interview_management_system.controller;

import interview_management_system.dto.CreateInterviewerRequest;
import interview_management_system.service.InterviewerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/interviewers")
@RequiredArgsConstructor
public class InterviewerController {

    private final InterviewerService interviewerService;

    @PostMapping
    public String createInterviewer(
            @RequestBody CreateInterviewerRequest request) {

        return interviewerService.createInterviewer(request);
    }
}