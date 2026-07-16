package interview_management_system.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateInterviewerRequest {

    private String fullName;

    private String email;

    private String password;

    private String designation;

    private String specialization;
}