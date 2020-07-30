package br.com.bjbraz.poll.repository;

import br.com.bjbraz.poll.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    public Optional<Vote> findByPollOptionId(Long pollOptionId);

    public List<Vote> findByPollIdOrderByPollOptionId(Long pollId);

}
