package br.com.bjbraz.poll.repository;

import br.com.bjbraz.poll.entity.VoteView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VoteViewRepository extends JpaRepository<VoteView, Long> {

    @Query(name = "SELECT count(*) FROM poll_audit where poll_id = ?1", nativeQuery = true)
    public Integer countByPool(Integer pollId);

}
