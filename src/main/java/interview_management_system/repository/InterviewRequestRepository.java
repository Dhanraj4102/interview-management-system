package interview_management_system.repository;

import interview_management_system.entity.Candidate;
import interview_management_system.entity.InterviewRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface InterviewRequestRepository
        extends JpaRepository<InterviewRequest, Long> {

    List<InterviewRequest> findByCandidate(Candidate candidate);

    @Query("""
       SELECT ir
       FROM InterviewRequest ir
       WHERE LOWER(ir.companyName) LIKE LOWER(CONCAT('%', :keyword, '%'))
          OR LOWER(ir.candidate.user.fullName) LIKE LOWER(CONCAT('%', :keyword, '%'))
       """)
    List<InterviewRequest> searchInterviews(@Param("keyword") String keyword);

    List<InterviewRequest> findByStatus(String status);

    List<InterviewRequest> findByInterviewResult(String interviewResult);

    long countByStatus(String status);

    long countByInterviewResult(String interviewResult);

    long countByInterviewDate(LocalDate interviewDate);
}




