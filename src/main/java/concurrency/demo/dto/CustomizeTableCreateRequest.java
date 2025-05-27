package concurrency.demo.dto;

import concurrency.demo.domain.CustomizeTable;
import concurrency.demo.domain.CustomizeTimeBox;
import concurrency.demo.domain.Member;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record CustomizeTableCreateRequest(
        CustomizeTableInfoCreateRequest info,
        List<CustomizeTimeBoxCreateRequest> table
) {

    public CustomizeTable toTable(Member member) {
        return info.toTable(member);
    }

    public List<CustomizeTimeBox> toTimeBoxes(CustomizeTable customizeTable) {
        return IntStream.range(0, table.size())
                .mapToObj(i -> table.get(i).toTimeBox(customizeTable, i + 1))
                .collect(Collectors.toList());
    }
}
