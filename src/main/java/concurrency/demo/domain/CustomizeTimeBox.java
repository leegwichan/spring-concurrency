package concurrency.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CustomizeTimeBox {

    public static final int TIME_MULTIPLIER = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id")
    private CustomizeTable customizeTable;

    private int sequence;

    @Enumerated(EnumType.STRING)
    private Stance stance;

    private int time;

    private String speaker;

    private String speechType;

    private Integer timePerTeam;

    private Integer timePerSpeaking;

    public CustomizeTimeBox(CustomizeTable customizeTable,
                            int sequence,
                            Stance stance,
                            String speechType,
                            Integer time,
                            Integer timePerTeam,
                            Integer timePerSpeaking,
                            String speaker) {
        this.customizeTable = customizeTable;
        this.sequence = sequence;
        this.stance = stance;
        this.time = time;
        this.speaker = initializeSpeaker(speaker);
        this.speechType = speechType;
        this.timePerTeam = timePerTeam;
        this.timePerSpeaking = timePerSpeaking;
    }

    private String initializeSpeaker(String speaker) {
        if (speaker == null || speaker.isBlank()) {
            return null;
        }
        return speaker;
    }

    protected CustomizeTimeBox() {
    }

    public Long getId() {
        return id;
    }

    public CustomizeTable getCustomizeTable() {
        return customizeTable;
    }

    public int getSequence() {
        return sequence;
    }

    public Stance getStance() {
        return stance;
    }

    public int getTime() {
        return time;
    }

    public String getSpeaker() {
        return speaker;
    }

    public String getSpeechType() {
        return speechType;
    }

    public Integer getTimePerTeam() {
        return timePerTeam;
    }

    public Integer getTimePerSpeaking() {
        return timePerSpeaking;
    }
}
