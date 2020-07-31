package br.com.bjbraz.poll.repository;

import br.com.bjbraz.poll.entity.PollAudit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollAuditRepository extends JpaRepository<PollAudit, Long> {

    long countByPollId(Long pollId);

    public PollAudit findByPollId(Long pollId);

}
