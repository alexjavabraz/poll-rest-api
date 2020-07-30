package br.com.bjbraz.poll.repository;

import br.com.bjbraz.poll.entity.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollRepository extends JpaRepository<Poll, Long> {

}
