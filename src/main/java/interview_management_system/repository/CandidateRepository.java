package interview_management_system.repository;

import interview_management_system.entity.Candidate;
import interview_management_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    Optional<Candidate> findByUser(User user);
    boolean existsByUser(User user);
}