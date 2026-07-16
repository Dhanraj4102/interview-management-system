package interview_management_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "interview_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InterviewRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "candidate_id", nullable = false)
    private Candidate candidate;

    private String companyName;

    private String hrName;

    private String hrContactNumber;

    private String hrEmailId;

    private LocalDate interviewDate;

    private LocalTime interviewTime;

    private String interviewMode;

    private String interviewRound;

    private String status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String interviewResult;

    private String notes;
}