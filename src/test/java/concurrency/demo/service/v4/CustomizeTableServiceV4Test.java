package concurrency.demo.service.v4;

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

@ActiveProfiles("v4")
class CustomizeTableServiceV4Test extends ServiceTest {

    @Autowired
    private CustomizeTableService customizeService;

    @Test
    void updateTableTest_whenRunAtSameTime_updateNormally() throws InterruptedException {
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
        runAtSameTime(10, () -> customizeService.updateTable(request, table.getId(), member.getId()));

        // Then
        assertThat(customizeTimeBoxRepository.count()).isEqualTo(2);
    }
}
