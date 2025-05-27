package concurrency.demo.dto;

import concurrency.demo.domain.CustomizeTable;
import concurrency.demo.domain.Member;

public record CustomizeTableInfoCreateRequest(
        String name,

        String agenda,

        String prosTeamName,

        String consTeamName,

        boolean warningBell,
        boolean finishBell
) {

    public CustomizeTable toTable(Member member) {
        return new CustomizeTable(member, name, agenda, warningBell, finishBell, prosTeamName, consTeamName);
    }
}
