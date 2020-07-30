package br.com.bjbraz.poll.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Poll Option Not Found")
public class PollOptionNotFoundException extends Exception {
    public PollOptionNotFoundException(){
        super("Poll Option Not Found.");
    }
}