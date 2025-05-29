# 

## 해결 방안 3 - 조회&수정 로직을 조회 없이 수정하도록 변경

### 관련 로직
```java
@Transactional
public void updateTable(CustomizeTableCreateRequest tableCreateRequest, long tableId, long memberId) {
    // ...

    timeBoxRepository.deleteAllByTable(existingTable); // 조회&수정 로직을 바로 수정하도록 함
    timeBoxRepository.saveAll(tableCreateRequest.toTimeBoxes(existingTable));
}
```
