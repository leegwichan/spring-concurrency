# 

## 문제 상황
![image](./images/concurrency_problem_product1.png)
![image](./images/concurrency_problem_product2.png)

### 데이터베이스 상황
![image](./images/concurrency_problem_database.png)

### 관련 로직
```java
@Transactional
public void updateTable(CustomizeTableCreateRequest tableCreateRequest, long tableId, long memberId) {
    // ...

    List<CustomizeTimeBox> existingTimeBoxes = timeBoxRepository.findAllByTable(existingTable);
    timeBoxRepository.deleteAll(existingTimeBoxes);
    timeBoxRepository.saveAll(tableCreateRequest.toTimeBoxes(existingTable));
}
```
