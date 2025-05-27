package concurrency.demo.repository;

import concurrency.demo.domain.CustomizeTable;
import concurrency.demo.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomizeTableRepository extends JpaRepository<CustomizeTable, Long> {

    Optional<CustomizeTable> findByIdAndMember(long tableId, Member member);
}
