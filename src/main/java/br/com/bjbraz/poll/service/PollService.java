package br.com.bjbraz.poll.service;

import br.com.bjbraz.poll.dto.PollOptionResponseDto;
import br.com.bjbraz.poll.dto.PollRequestDto;
import br.com.bjbraz.poll.dto.PollResponseDto;
import br.com.bjbraz.poll.dto.VoteRequestDto;
import br.com.bjbraz.poll.entity.Poll;
import br.com.bjbraz.poll.entity.PollOption;
import br.com.bjbraz.poll.entity.Vote;
import br.com.bjbraz.poll.exception.PollOptionNotFoundException;
import br.com.bjbraz.poll.repository.PollOptionRepository;
import br.com.bjbraz.poll.repository.PollRepository;
import br.com.bjbraz.poll.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PollService {

    @Autowired
    private PollRepository repository;

    @Autowired
    private PollOptionRepository pollOptionRepository;

    @Autowired
    private VoteRepository voteRepository;

    public List<PollResponseDto> listAllPolls(){
        List<PollResponseDto> retorno = new ArrayList<>();
        List<Poll> polls = repository.findAll();

        if(polls.isEmpty())
            return retorno;

        retorno = (polls.stream().map(s -> {
            PollResponseDto resp = new PollResponseDto();
            resp.setDescription(s.getDescription());
            resp.setId(s.getId());
            resp.setOptions(s.getOptions().stream().map(op -> new PollOptionResponseDto(op)).collect(Collectors.toList()));
            return resp;
        }).collect(Collectors.toList()));

        return retorno;
    }

    public PollResponseDto findPollById(Long id){
        Optional<Poll> optPoll = repository.findById(id);

        if(optPoll.isPresent())
            return new PollResponseDto(optPoll.get());

        return null;
    }

    public PollResponseDto savePoll(PollRequestDto poll){
        Poll newObject = new Poll();
        newObject.setDescription(poll.getDesc());
        newObject = repository.save(newObject);

        Poll finalNewObject = newObject;
        newObject.setOptions(poll.getOptions().stream().map(s ->
                {
                    PollOption po = new PollOption();
                    po.setDescription(s);
                    po.setPoll(finalNewObject);
                    return po;
                }).collect(Collectors.toList())
        );
        newObject = repository.save(newObject);
        PollResponseDto objectToReturn = new PollResponseDto(newObject.getId());

        return objectToReturn;
    }

    public void vote(Long pollId, VoteRequestDto pollOptionId) throws PollOptionNotFoundException {
        PollOption option = pollOptionRepository.findByIdAndPollId(pollOptionId.getId(), pollId).orElseThrow(() -> new PollOptionNotFoundException() );
        Vote vote = voteRepository.findByPollOptionId(option.getId()).orElse(new Vote(option));
        vote.setQuantity(vote.getQuantity() + 1);
        voteRepository.save(vote);
    }

    public void stats(Long pollId) throws PollOptionNotFoundException {
        Poll poll = repository.findById(pollId).orElseThrow(() -> new PollOptionNotFoundException());
        List<Vote> votes = voteRepository.findByPollIdOrderByPollOptionId(poll.getId());

        try{
            votes
        }catch(Exception e){

        }

    }
}
