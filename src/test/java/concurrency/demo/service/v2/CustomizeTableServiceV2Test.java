package concurrency.demo.service.v2;

import static org.assertj.core.api.Assertions.assertThat;

import concurrency.demo.domain.CustomizeTable;
import concurrency.demo.domain.CustomizeTimeBox;
import concurrency.demo.domain.Member;
import concurrency.demo.domain.Stance;
import concurrency.demo.dto.CustomizeTableCreateRequest;
import concurrency.demo.dto.CustomizeTableInfoCreateRequest;
import concurrency.demo.dto.CustomizeTimeBoxCreateRequest;
import concurrency.demo.service.CustomizeTableService;
import concurrency.demo.service.ServiceTest;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("v2")
class CustomizeTableServiceV2Test extends ServiceTest {

    @Autowired
    private CustomizeTableService customizeService;

    @Test
    void updateTableTest_whenRunAtSameTime_savedDuplicated() throws InterruptedException {
        // Given (Setup)
        Member member = memberRepository.save(new Member("default@gmail.com"));
        CustomizeTable table = customizeTableRepository.save(
                new CustomizeTable(member, "자유 테이블", "주제", true, true, "찬성", "반대")
        );
        customizeTimeBoxRepository.saveAll(List.of(
                new CustomizeTimeBox(table, 1, Stance.NEUTRAL, "입론1", 240, 120, 60, "1"),
                new CustomizeTimeBox(table, 1, Stance.NEUTRAL, "입론1", 240, 120, 60, "2")
        ));

        // Given (Request)
        CustomizeTableCreateRequest request = new CustomizeTableCreateRequest(
                new CustomizeTableInfoCreateRequest("자유 테이블", "주제", "찬성",
                        "반대", true, true),
                List.of(
                        new CustomizeTimeBoxCreateRequest(Stance.PROS, "입론1", 120, 60, null, "발언자1"),
                        new CustomizeTimeBoxCreateRequest(Stance.PROS, "입론2", 120, 60, null, "발언자2")
                ));

        // When
        runAtSameTime(2, () -> customizeService.updateTable(request, table.getId(), member.getId()));

        // Then
        assertThat(customizeTimeBoxRepository.count()).isEqualTo(2);
        /*
        * Problem : 데드락 발생
        *  Deadlock detected. The current transaction was rolled back
        * */
    }
}
