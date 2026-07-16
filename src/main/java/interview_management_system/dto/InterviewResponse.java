package interview_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InterviewResponse {

    private Long id;

    private Long candidateId;

    private String candidateName;

    private String candidateEmail;

    private String candidatePhone;

    private Integer candidateExperienceYears;

    private String candidateSkills;

    private String companyName;

    private String hrName;

    private String hrContactNumber;

    private String hrEmailId;

    private LocalDate interviewDate;

    private LocalTime interviewTime;

    private String interviewMode;

    private String interviewRound;

    private String status;

    private String interviewResult;

    private String notes;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}