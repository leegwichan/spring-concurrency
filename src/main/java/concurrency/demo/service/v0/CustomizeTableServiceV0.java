package concurrency.demo.service.v0;

import concurrency.demo.domain.CustomizeTable;
import concurrency.demo.domain.CustomizeTimeBox;
import concurrency.demo.domain.Member;
import concurrency.demo.dto.CustomizeTableCreateRequest;
import concurrency.demo.repository.CustomizeTableRepository;
import concurrency.demo.repository.CustomizeTimeBoxRepository;
import concurrency.demo.repository.MemberRepository;
import concurrency.demo.service.CustomizeTableService;
import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Profile("v0")
public class CustomizeTableServiceV0 implements CustomizeTableService {

    private final MemberRepository memberRepository;
    private final CustomizeTableRepository tableRepository;
    private final CustomizeTimeBoxRepository timeBoxRepository;

    public CustomizeTableServiceV0(MemberRepository memberRepository,
                                   CustomizeTableRepository tableRepository,
                                   CustomizeTimeBoxRepository timeBoxRepository) {
        this.memberRepository = memberRepository;
        this.tableRepository = tableRepository;
        this.timeBoxRepository = timeBoxRepository;
    }

    @Override
    @Transactional
    public void updateTable(CustomizeTableCreateRequest tableCreateRequest, long tableId, long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found for the given ID"));
        CustomizeTable existingTable = tableRepository.findByIdAndMember(tableId, member)
                .orElseThrow(() -> new RuntimeException("Table not found for the given ID and member ID"));
        CustomizeTable renewedTable = tableCreateRequest.toTable(member);
        existingTable.updateTable(renewedTable);

        List<CustomizeTimeBox> existingTimeBoxes = timeBoxRepository.findAllByTable(existingTable);
        timeBoxRepository.deleteAll(existingTimeBoxes);
        timeBoxRepository.saveAll(tableCreateRequest.toTimeBoxes(existingTable));
    }
}
