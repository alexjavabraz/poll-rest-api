package br.com.bjbraz.poll.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "poll_audit")
public class VoteView {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date accessTimeStamp;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="poll_id", nullable=false)
    private Poll poll;

}
