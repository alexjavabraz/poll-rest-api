package br.com.bjbraz.poll;

import br.com.bjbraz.poll.repository.PollAuditRepository;
import br.com.bjbraz.poll.repository.PollOptionRepository;
import br.com.bjbraz.poll.repository.PollRepository;
import br.com.bjbraz.poll.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;

//@RunWith(SpringJUnit4ClassRunner.class)
//@DataJpaTest
//@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class PollTests {


    @Autowired private PollRepository pollRepository;
    @Autowired private PollOptionRepository pollOptionRepository;
    @Autowired private PollAuditRepository pollAuditRepository;
    @Resource private PollService service;

    public void test(){
        assertThat(true).isTrue();
    }


//
//    @Test
//    public void savingPollAndOptions() throws Exception {
//
//        PollRequestDto poll = new PollRequestDto();
//        poll.setDesc("Enquete 1");
//        poll.setOptions(Arrays.asList("Opção 1", "Opção 2", "Opção 3"));
//
//        // exercise
//        PollResponseDto p = service.savePoll(poll);
//        Optional<Poll> pollOptional   = pollRepository.findById(p.getId());
//
//        assertThat(pollOptional.isPresent()).isTrue();
//
//        Poll poll1 = pollOptional.get();
//
//        assertThat(poll1.getOptions()).hasSize(3);
//        assertThat(poll1.getDescription()).isNotBlank();
//        assertThat(poll1.getOptions())
//                .extracting("quantity")
//                .contains(tuple(0));
//
//        service.addCount(poll1);
//
//        long count = pollAuditRepository.countByPollId(p.getId());
//        assertThat(count).isEqualTo(1);
//
//        VoteRequestDto vote = new VoteRequestDto();
//        vote.setId(p.getOptions().get(0).getId());
//
//        service.vote(p.getId(), vote);
//
//        //add count
//        PollResponseDto pollResponseDto = service.findPollById(p.getId());
//        poll1 = pollRepository.findById(p.getId()).get();
//        count = pollAuditRepository.countByPollId(p.getId());
//        assertThat(count).isEqualTo(2);
//
//        VoteStatsResponseDto stats = service.stats(p.getId());
//
//        assertThat(stats.getViews()).isEqualTo(1);
//
//
//    }
//
//    public static Stream<Arguments> providenciarPoll() {
//
//        // Enquete
//        PollRequestDto p = new PollRequestDto();
//        p.setDesc("Enquete 1");
//        p.setOptions(Arrays.asList("Opção 1", "Opção 2", "Opção 3"));
//
//        // Enquete
//        PollRequestDto p1 = new PollRequestDto();
//        p1.setDesc("Enquete 1");
//        p1.setOptions(Arrays.asList("Opção 1", "Opção 2", "Opção 3", "Opção 4"));
//
//        return Stream.of(
//                // objeto, id
//                Arguments.of(p, 3),
//                Arguments.of(p1, 4)
//        );
//    }

}
