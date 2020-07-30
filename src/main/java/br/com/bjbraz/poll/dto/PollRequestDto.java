package br.com.bjbraz.poll.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@ToString
public class PollRequestDto {

    @NotBlank(message="field poll_description is required")
    @JsonProperty("poll_description")
    private String desc;

    @NotEmpty(message = "field options is required.")
    @JsonProperty("options")
    private List<String> options;
}
