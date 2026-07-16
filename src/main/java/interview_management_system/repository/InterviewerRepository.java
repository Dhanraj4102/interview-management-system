package interview_management_system.repository;

import interview_management_system.entity.Interviewer;
import interview_management_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InterviewerRepository
        extends JpaRepository<Interviewer, Long> {

    Optional<Interviewer> findByUser(User user);

    boolean existsByUser(User user);
}