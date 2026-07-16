package interview_management_system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateInterviewRequest {

    @NotBlank(message = "Company name is required")
    private String companyName;

    @NotBlank(message = "HR name is required. Enter NA if not available")
    private String hrName;

    @NotBlank(message = "HR contact number is required. Enter NA if not available")
    private String hrContactNumber;

    @NotBlank(message = "HR email is required. Enter NA if not available")
    private String hrEmailId;

    @NotNull(message = "Interview date is required")
    private LocalDate interviewDate;

    @NotNull(message = "Interview time is required")
    private LocalTime interviewTime;

    @NotBlank(message = "Interview mode is required")
    private String interviewMode;

    @NotBlank(message = "Interview round is required")
    private String interviewRound;

    @NotBlank(message = "Interview status is required")
    private String status;

    @NotBlank(message = "Interview result is required")
    private String interviewResult;

    private String notes;
}