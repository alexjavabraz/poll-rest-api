package br.com.bjbraz.poll.dto;

import br.com.bjbraz.poll.entity.PollOption;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "option_id", "option_description" })
public class PollOptionResponseDto {

    public PollOptionResponseDto(PollOption po){
        this.id = po.getId();
        this.description = po.getDescription();
    }

    @JsonProperty("option_id")
    private Long id;

    @JsonProperty("option_description")
    private String description;

}
