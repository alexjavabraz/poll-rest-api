package br.com.bjbraz.poll.dto;

import br.com.bjbraz.poll.entity.Poll;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "poll_id", "poll_description", "options" })
public class PollResponseDto {

    public PollResponseDto(Long id){
        this.id = id;
    }

    public PollResponseDto(Poll p){
        this.id = p.getId();
        this.description = p.getDescription();
        this.options = new ArrayList<>();

        if(null != p.getOptions() && p.getOptions().size() > 0){
            this.options = p.getOptions().stream().map(s -> {
                return new PollOptionResponseDto(s);
            }).collect(Collectors.toList());
        }
    }

    @JsonProperty("poll_id")
    private Long id;

    @JsonProperty("poll_description")
    private String description;

    @JsonProperty("options")
    private List<PollOptionResponseDto> options;

}
