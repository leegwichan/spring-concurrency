package concurrency.demo.dto;


import concurrency.demo.domain.CustomizeTable;
import concurrency.demo.domain.CustomizeTimeBox;
import concurrency.demo.domain.Stance;
import jakarta.annotation.Nullable;

public record CustomizeTimeBoxCreateRequest(
        Stance stance,
        String speechType,
        @Nullable Integer time,
        @Nullable Integer timePerTeam,
        @Nullable Integer timePerSpeaking,
        @Nullable String speaker
) {

    public CustomizeTimeBox toTimeBox(CustomizeTable customizeTable, int sequence) {
        return new CustomizeTimeBox(
                customizeTable,
                sequence,
                stance,
                speechType,
                time,
                timePerTeam,
                timePerSpeaking,
                speaker
        );
    }
}
