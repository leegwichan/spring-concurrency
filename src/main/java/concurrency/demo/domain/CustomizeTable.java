package concurrency.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class CustomizeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String name;

    private String agenda;

    private String prosTeamName;

    private String consTeamName;

    private boolean warningBell;

    private boolean finishBell;

    private LocalDateTime usedAt;

    public CustomizeTable(
            Member member,
            String name,
            String agenda,
            boolean warningBell,
            boolean finishBell,
            String prosTeamName,
            String consTeamName
    ) {
        this.member = member;
        this.name = name;
        this.agenda = agenda;
        this.prosTeamName = prosTeamName;
        this.consTeamName = consTeamName;
        this.warningBell = warningBell;
        this.finishBell = finishBell;
        this.usedAt = LocalDateTime.now();
    }

    public void updateTable(CustomizeTable renewTable) {
        this.name = renewTable.getName();
        this.agenda = renewTable.getAgenda();
        this.prosTeamName = renewTable.getProsTeamName();
        this.consTeamName = renewTable.getConsTeamName();
        this.warningBell = renewTable.isWarningBell();
        this.finishBell = renewTable.isFinishBell();
        this.usedAt = LocalDateTime.now();
    }

    protected CustomizeTable() {
    }

    public void updateUsedAt() {
        this.usedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }

    public String getName() {
        return name;
    }

    public String getAgenda() {
        return agenda;
    }

    public String getProsTeamName() {
        return prosTeamName;
    }

    public String getConsTeamName() {
        return consTeamName;
    }

    public boolean isWarningBell() {
        return warningBell;
    }

    public boolean isFinishBell() {
        return finishBell;
    }

    public LocalDateTime getUsedAt() {
        return usedAt;
    }
}

