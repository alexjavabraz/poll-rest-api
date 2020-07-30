package br.com.bjbraz.poll.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class VoteRequestDto {

    @NotNull
    @JsonProperty("option_id")
    private Long id;

}
