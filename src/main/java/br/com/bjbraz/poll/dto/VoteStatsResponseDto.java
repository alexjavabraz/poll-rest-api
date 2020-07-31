package br.com.bjbraz.poll.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@JsonPropertyOrder({ "views", "votes" })
@Getter
@Setter
@ToString
public class VoteStatsResponseDto {

    @JsonProperty("views")
    private Long views;

    @JsonProperty("votes")
    private List<PollOptionResponseDto> optionAndQuantity;
}
