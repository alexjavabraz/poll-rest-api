package br.com.bjbraz.poll.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/poll", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = {"Pool API"})
public class PollController {

    @ApiOperation( value = "${swagger.operations")
    public ResponseEntity<String> test(){
        return new ResponseEntity<String>("teste", HttpStatus.OK);
    }



}
