package concurrency.demo.service.v3;

import concurrency.demo.domain.CustomizeTable;
import concurrency.demo.domain.Member;
import concurrency.demo.dto.CustomizeTableCreateRequest;
import concurrency.demo.repository.CustomizeTableRepository;
import concurrency.demo.repository.CustomizeTimeBoxRepository;
import concurrency.demo.repository.MemberRepository;
import concurrency.demo.service.CustomizeTableService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Profile("v3")
@Service
public class CustomizeTableServiceV3 implements CustomizeTableService {

    private final MemberRepository memberRepository;
    private final CustomizeTableRepository tableRepository;
    private final CustomizeTimeBoxRepository timeBoxRepository;

    public CustomizeTableServiceV3(MemberRepository memberRepository, CustomizeTableRepository tableRepository,
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

        timeBoxRepository.deleteAllByTable(existingTable); // 조회 -> 수정 로직을 바로 수정하도록 함
        timeBoxRepository.saveAll(tableCreateRequest.toTimeBoxes(existingTable));
    }
}
