package br.com.bjbraz.poll.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonPropertyOrder({ "option_id", "qty" })
@Getter
@Setter
@ToString
public class VoteStatsQuantityResponseDto {

    @JsonProperty("option_id")
    private int id;

    @JsonProperty("qty")
    private int quantidade;
}
