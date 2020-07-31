package br.com.bjbraz.poll.controller;

import br.com.bjbraz.poll.dto.PollRequestDto;
import br.com.bjbraz.poll.dto.PollResponseDto;
import br.com.bjbraz.poll.dto.VoteRequestDto;
import br.com.bjbraz.poll.dto.VoteStatsResponseDto;
import br.com.bjbraz.poll.entity.Poll;
import br.com.bjbraz.poll.entity.PollOption;
import br.com.bjbraz.poll.repository.PollRepository;
import br.com.bjbraz.poll.service.PollService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/poll", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = {"Pool API"})
public class PollController {

    @Autowired
    private PollService service;

    @ApiOperation( value = "List all poll objects")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success.")})
    @GetMapping(path = "/all")
    public ResponseEntity<List<PollResponseDto>> findAll(){
        List<PollResponseDto> polls = service.listAllPolls();

        if(polls.isEmpty())
            new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        return new ResponseEntity<List<PollResponseDto>>(polls, HttpStatus.OK);
    }

    @ApiOperation(	value = "Find a Poll by ID",
            extensions = {@Extension(properties = { @ExtensionProperty(name = "description", value = "Find Poll by poll_id, if the poll_id not found, this method returns HTTP.NOT_FOUND.") })}
    )
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success."), @ApiResponse(code = 404, message = "Poll not found.")})
    @ApiParam(name = "body", value = "Poll object for creation.", required = true)
    @GetMapping(path = "/{id}")
    public ResponseEntity<PollResponseDto> searchById(@PathVariable Long id) throws Exception {
        PollResponseDto optPoll = service.findPollById(id);

        if(optPoll != null)
            return new ResponseEntity<>(optPoll, HttpStatus.OK);

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @ApiOperation(	value = "Add new Poll",
            extensions = {@Extension(properties = {
                    @ExtensionProperty(
                            name = "description",
                            value = "Creates a new Poll") })}
    )
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success.")})
    @ApiParam(name = "body", value = "Poll object for creation.", required = true)
    @PostMapping(path = "")
    public ResponseEntity<PollResponseDto> add(@RequestBody @Valid PollRequestDto poll) throws Exception {
       return new ResponseEntity<>(service.savePoll(poll), HttpStatus.OK);
    }

    @ApiOperation(	value = "Vote in a Poll",
            extensions = {@Extension(properties = {
                    @ExtensionProperty(
                            name = "description",
                            value = "Vote in a Poll") })}
    )
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success."), @ApiResponse(code = 404, message = "Poll or Poll/Option not found.")})
    @ApiParam(name = "body", value = "Send Poll id for the option.", required = true)
    @PostMapping(path = "/{id}/vote")
    public ResponseEntity<Void> vote(@Valid @PathVariable Long id, @RequestBody @Valid VoteRequestDto poll) throws Exception {
        service.vote(id, poll);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(	value = "Show Poll statistics",
            extensions = {@Extension(properties = {
                    @ExtensionProperty(
                            name = "description",
                            value = "Show Poll statistics, quantity of votes in each poll option") })}
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success."),
            @ApiResponse(code = 404, message = "Poll not found.")})
    @ApiParam(name = "body", value = "Send Poll id for the option.", required = true)
    @PostMapping(path = "/{id}/stats")
    public ResponseEntity<VoteStatsResponseDto> stats(@Valid @PathVariable Long id) throws Exception {
        return new ResponseEntity<>(service.stats(id), HttpStatus.OK);
    }

}
