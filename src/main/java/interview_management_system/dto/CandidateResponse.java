package interview_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidateResponse {

    private Long id;

    private String fullName;

    private String email;

    private String phone;

    private Integer experienceYears;

    private String skills;

    private String employmentStatus;

    private String currentLocation;

    private String linkedinUrl;

    private String resumeUrl;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}