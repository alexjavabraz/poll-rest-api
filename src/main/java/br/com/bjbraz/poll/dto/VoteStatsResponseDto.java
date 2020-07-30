package br.com.bjbraz.poll.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({ "views", "votes" })
public class VoteStatsResponseDto {

    @JsonProperty("views")
    private int views;

    @JsonProperty("votes")
    private List<PollOptionResponseDto> optionAndQuantity;
}
