# 

## 해결 방안 2 - 부모 객체의 비관적 락 이용

### 관련 로직
```java
@Transactional
public void updateTable(CustomizeTableCreateRequest tableCreateRequest, long tableId, long memberId) {
    // Table 조회 시, 쓰기 락을 가져옴
    CustomizeTable existingTable = tableRepository.findByIdAndMemberWithLock(tableId, member)
            .orElseThrow(() -> new RuntimeException("Table not found for the given ID and member ID"));
    // ...

    List<CustomizeTimeBox> existingTimeBoxes = timeBoxRepository.findAllByTable(existingTable);
    timeBoxRepository.deleteAll(existingTimeBoxes);
    timeBoxRepository.saveAll(tableCreateRequest.toTimeBoxes(existingTable));
}

public interface TableRepository extends JpaRepository<CustomizeTable, Long> {
    // ...

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT c FROM CustomizeTable c WHERE c.id = :id AND c.member = :member")
    Optional<CustomizeTable> findByIdAndMemberWithLock(long id, Member member);
}
```
