package br.com.bjbraz.poll.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "option_id", "qty" })
public class VoteStatsQuantityResponseDto {

    @JsonProperty("option_id")
    private int id;

    @JsonProperty("qty")
    private int quantidade;
}
