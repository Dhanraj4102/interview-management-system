package interview_management_system.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardResponse {

    private long totalCandidates;

    private long totalInterviews;

    private long pendingInterviews;

    private long completedInterviews;

    private long selectedCandidates;

    private long rejectedCandidates;

    private long todaysInterviews;
}