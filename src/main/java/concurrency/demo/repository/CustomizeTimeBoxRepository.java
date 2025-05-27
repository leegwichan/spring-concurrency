package concurrency.demo.repository;

import concurrency.demo.domain.CustomizeTable;
import concurrency.demo.domain.CustomizeTimeBox;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CustomizeTimeBoxRepository extends JpaRepository<CustomizeTimeBox, Long> {

    // v1, v2, v3
    List<CustomizeTimeBox> findAllByTable(CustomizeTable existingTable);

    @Query("DELETE FROM CustomizeTimeBox ctb WHERE ctb IN :timeBoxes")
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    void deleteAll(List<CustomizeTimeBox> timeBoxes);

    // v4
    @Query("DELETE FROM CustomizeTimeBox ctb WHERE ctb.table = :table")
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    void deleteAllByTable(CustomizeTable table);
}
