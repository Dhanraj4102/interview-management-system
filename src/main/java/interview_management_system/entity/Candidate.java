package interview_management_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "candidates")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

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