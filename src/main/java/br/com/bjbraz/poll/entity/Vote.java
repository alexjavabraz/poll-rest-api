package br.com.bjbraz.poll.entity;

import lombok.*;
import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vote")
public class Vote {

    public Vote(PollOption option){
        this.pollOption = option;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private @Getter
    Long id;

    @Getter
    @Setter
    private int quantity;

    @ManyToOne
    private PollOption pollOption;

    @ManyToOne
    private Poll poll;

}
