package concurrency.demo.repository;

import concurrency.demo.domain.CustomizeTable;
import concurrency.demo.domain.Member;
import jakarta.persistence.LockModeType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

public interface CustomizeTableRepository extends JpaRepository<CustomizeTable, Long> {

    // v1, v2, v4
    Optional<CustomizeTable> findByIdAndMember(long tableId, Member member);

    // v3
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT c FROM CustomizeTable c WHERE c.id = :id AND c.member = :member")
    Optional<CustomizeTable> findByIdAndMemberWithLock(long id, Member member);
}
