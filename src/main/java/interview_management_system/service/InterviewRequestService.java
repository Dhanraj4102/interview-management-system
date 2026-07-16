package interview_management_system.service;

import interview_management_system.dto.CreateInterviewRequest;
import interview_management_system.dto.DashboardResponse;
import interview_management_system.dto.InterviewResponse;
import interview_management_system.dto.UpdateInterviewRequest;
import interview_management_system.entity.Candidate;
import interview_management_system.entity.InterviewRequest;
import interview_management_system.entity.User;
import interview_management_system.exception.ResourceNotFoundException;
import interview_management_system.repository.CandidateRepository;
import interview_management_system.repository.InterviewRequestRepository;
import interview_management_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InterviewRequestService {

    private final InterviewRequestRepository interviewRequestRepository;
    private final UserRepository userRepository;
    private final CandidateRepository candidateRepository;

    public String createInterviewRequest(
            String authenticatedEmail,
            CreateInterviewRequest request) {

        User user = userRepository.findByEmail(authenticatedEmail)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Candidate candidate = candidateRepository.findByUser(user)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Candidate profile not found"
                        ));

        InterviewRequest interviewRequest = InterviewRequest.builder()
                .candidate(candidate)
                .companyName(request.getCompanyName())
                .hrName(request.getHrName())
                .hrContactNumber(request.getHrContactNumber())
                .hrEmailId(request.getHrEmailId())
                .interviewDate(request.getInterviewDate())
                .interviewTime(request.getInterviewTime())
                .interviewMode(request.getInterviewMode())
                .interviewRound(request.getInterviewRound())
                .status("PENDING")
                .interviewResult("PENDING")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        interviewRequestRepository.save(interviewRequest);

        return "Interview Request Created Successfully";
    }

    public List<InterviewResponse> getInterviewRequests(
            String authenticatedEmail) {

        User user = userRepository.findByEmail(authenticatedEmail)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Candidate candidate = candidateRepository.findByUser(user)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Candidate profile not found"
                        ));

        return interviewRequestRepository.findByCandidate(candidate)
                .stream()
                .map(this::mapToInterviewResponse)
                .toList();
    }

    public List<InterviewResponse> getAllInterviewRequests() {

        return interviewRequestRepository.findAll()
                .stream()
                .map(this::mapToInterviewResponse)
                .toList();
    }

    public InterviewResponse updateInterviewRequest(
            Long id,
            UpdateInterviewRequest request) {

        InterviewRequest interviewRequest =
                interviewRequestRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Interview request not found"
                                ));

        interviewRequest.setCompanyName(request.getCompanyName());
        interviewRequest.setHrName(request.getHrName());
        interviewRequest.setHrContactNumber(request.getHrContactNumber());
        interviewRequest.setHrEmailId(request.getHrEmailId());
        interviewRequest.setInterviewDate(request.getInterviewDate());
        interviewRequest.setInterviewTime(request.getInterviewTime());
        interviewRequest.setInterviewMode(request.getInterviewMode());
        interviewRequest.setInterviewRound(request.getInterviewRound());
        interviewRequest.setStatus(request.getStatus());
        interviewRequest.setInterviewResult(request.getInterviewResult());
        interviewRequest.setNotes(request.getNotes());
        interviewRequest.setUpdatedAt(LocalDateTime.now());

        InterviewRequest updatedInterview =
                interviewRequestRepository.save(interviewRequest);

        return mapToInterviewResponse(updatedInterview);
    }

    public List<InterviewResponse> searchInterviews(String keyword) {

        return interviewRequestRepository.searchInterviews(keyword)
                .stream()
                .map(this::mapToInterviewResponse)
                .toList();
    }

    public List<InterviewResponse> getInterviewsByStatus(String status) {

        return interviewRequestRepository.findByStatus(status)
                .stream()
                .map(this::mapToInterviewResponse)
                .toList();
    }

    public List<InterviewResponse> getInterviewsByResult(
            String interviewResult) {

        return interviewRequestRepository
                .findByInterviewResult(interviewResult)
                .stream()
                .map(this::mapToInterviewResponse)
                .toList();
    }

    public DashboardResponse getDashboard() {

        return DashboardResponse.builder()
                .totalCandidates(candidateRepository.count())
                .totalInterviews(interviewRequestRepository.count())
                .pendingInterviews(
                        interviewRequestRepository.countByStatus("PENDING")
                )
                .completedInterviews(
                        interviewRequestRepository.countByStatus("COMPLETED")
                )
                .selectedCandidates(
                        interviewRequestRepository
                                .countByInterviewResult("SELECTED")
                )
                .rejectedCandidates(
                        interviewRequestRepository
                                .countByInterviewResult("REJECTED")
                )
                .todaysInterviews(
                        interviewRequestRepository
                                .countByInterviewDate(LocalDate.now())
                )
                .build();
    }

    public String deleteInterviewRequest(Long id) {

        InterviewRequest interviewRequest =
                interviewRequestRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Interview request not found"
                                ));

        interviewRequestRepository.delete(interviewRequest);

        return "Interview Request Deleted Successfully";
    }

    private InterviewResponse mapToInterviewResponse(
            InterviewRequest interviewRequest) {

        Candidate candidate = interviewRequest.getCandidate();
        User user = candidate.getUser();

        return InterviewResponse.builder()
                .id(interviewRequest.getId())
                .candidateId(candidate.getId())
                .candidateName(user.getFullName())
                .candidateEmail(user.getEmail())
                .candidatePhone(candidate.getPhone())
                .candidateExperienceYears(candidate.getExperienceYears())
                .candidateSkills(candidate.getSkills())
                .companyName(interviewRequest.getCompanyName())
                .hrName(interviewRequest.getHrName())
                .hrContactNumber(interviewRequest.getHrContactNumber())
                .hrEmailId(interviewRequest.getHrEmailId())
                .interviewDate(interviewRequest.getInterviewDate())
                .interviewTime(interviewRequest.getInterviewTime())
                .interviewMode(interviewRequest.getInterviewMode())
                .interviewRound(interviewRequest.getInterviewRound())
                .status(interviewRequest.getStatus())
                .interviewResult(interviewRequest.getInterviewResult())
                .notes(interviewRequest.getNotes())
                .createdAt(interviewRequest.getCreatedAt())
                .updatedAt(interviewRequest.getUpdatedAt())
                .build();
    }
}