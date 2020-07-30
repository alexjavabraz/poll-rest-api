package br.com.bjbraz.poll.repository;

import br.com.bjbraz.poll.entity.PollOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PollOptionRepository extends JpaRepository<PollOption, Long> {

    public Optional<PollOption> findByIdAndPollId(Long optionId, Long pollId);
}
