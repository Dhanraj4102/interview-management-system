package interview_management_system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateInterviewRequest {

    @NotBlank(message = "Company name is required")
    private String companyName;

    @NotBlank(message = "HR name is required")
    private String hrName;

    @NotBlank(message = "HR contact number is required")
    private String hrContactNumber;

    @NotBlank(message = "HR email is required")
    private String hrEmailId;

    @NotNull(message = "Interview date is required")
    private LocalDate interviewDate;

    @NotNull(message = "Interview time is required")
    private LocalTime interviewTime;

    @NotBlank(message = "Interview mode is required")
    private String interviewMode;

    @NotBlank(message = "Interview round is required")
    private String interviewRound;
}