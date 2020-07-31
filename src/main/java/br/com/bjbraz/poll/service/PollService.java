package br.com.bjbraz.poll.service;

import br.com.bjbraz.poll.dto.*;
import br.com.bjbraz.poll.entity.Poll;
import br.com.bjbraz.poll.entity.PollAudit;
import br.com.bjbraz.poll.entity.PollOption;
import br.com.bjbraz.poll.exception.PollOptionNotFoundException;
import br.com.bjbraz.poll.repository.PollAuditRepository;
import br.com.bjbraz.poll.repository.PollOptionRepository;
import br.com.bjbraz.poll.repository.PollRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
    private PollAuditRepository pollAuditRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<PollResponseDto> listAllPolls(){
        logger.info("Listing all Polls");
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

        if(optPoll.isPresent()) {
            addCount(optPoll.get());
            return new PollResponseDto(optPoll.get());
        }

        logger.error("Poll not found on searching by Id {} ", id);
        return null;
    }

     public PollResponseDto savePoll(PollRequestDto poll){
         logger.info("Trying to save Poll {} ", poll.getDesc());

        Poll newObject = new Poll();
        newObject.setDescription(poll.getDesc());

        Poll finalNewObject = newObject;
        newObject.setOptions(poll.getOptions().stream().map(s ->
                {
                    PollOption po = new PollOption();
                    po.setDescription(s);
                    po.setPoll(finalNewObject);
                    po.setQuantity(0L);
                    return po;
                }).collect(Collectors.toList())
        );
        newObject = repository.save(newObject);
        PollResponseDto objectToReturn = new PollResponseDto(newObject.getId());

        return objectToReturn;
    }

    public VoteStatsResponseDto stats(Long pollId) throws PollOptionNotFoundException {
        Poll poll = repository.findById(pollId).orElseThrow(() -> new PollOptionNotFoundException());
        VoteStatsResponseDto response = new VoteStatsResponseDto();
        response.setViews(pollAuditRepository.countByPollId(pollId));

        response.setOptionAndQuantity(poll.getOptions().stream().map(s -> {
            PollOptionResponseDto por = new PollOptionResponseDto();
            por.setId(s.getId());
            por.setQuantity(s.getQuantity());
            return por;
        }).collect(Collectors.toList()));

        return response;
    }

    public void addCount(Poll poll) {
        PollAudit audit =  new PollAudit();
        audit.setAccessTimeStamp(new Date());
        audit.setPoll(poll);
        pollAuditRepository.save(audit);
    }


    public void vote(Long pollId, VoteRequestDto pollOption) throws PollOptionNotFoundException {
        PollOption opt = pollOptionRepository.findByIdAndPollId(pollOption.getId(), pollId).orElseThrow(() -> new PollOptionNotFoundException());
        opt.setQuantity(opt.getQuantity() + 1);
        pollOptionRepository.save(opt);
    }
}
