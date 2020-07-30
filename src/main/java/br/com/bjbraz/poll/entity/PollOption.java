package br.com.bjbraz.poll.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@Table(name = "poll_option")
public class PollOption {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    @Getter
    @Setter
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "poll_id", nullable = false)
    private @Getter @Setter Poll poll;
}
