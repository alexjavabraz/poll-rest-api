package br.com.bjbraz.poll.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "poll")
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private @Getter
    Long id;

    @Getter
    @Setter
    private String description;

    @OneToMany(mappedBy = "poll", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Getter
    @Setter
    private List<PollOption> options;

}