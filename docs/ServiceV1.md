# 

## 해결 방안 1 - 트랜잭션 격리 수준 조정

### 관련 로직
```java
@Transactional(isolation = Isolation.SERIALIZABLE) // 트랜잭션 격리 수준 증가
public void updateTable(CustomizeTableCreateRequest tableCreateRequest, long tableId, long memberId) {
    // ...

    List<CustomizeTimeBox> existingTimeBoxes = timeBoxRepository.findAllByTable(existingTable);
    timeBoxRepository.deleteAll(existingTimeBoxes);
    timeBoxRepository.saveAll(tableCreateRequest.toTimeBoxes(existingTable));
}
```

## 동시성 테스트 코드 작성

```java
protected void runAtSameTime(int count, Runnable task) throws InterruptedException {
    List<Thread> threads = IntStream.range(0, count)
            .mapToObj(i -> new Thread(task))
            .toList();

    threads.forEach(Thread::start);
    for (Thread thread : threads) {
        thread.join();
    }
}
```

## 새로 생긴 문제점

```text
Exception in thread "Thread-8" org.springframework.dao.CannotAcquireLockException: could not execute statement [Deadlock detected. The current transaction was rolled back. Details: "CUSTOMIZE_TABLE"; SQL statement:
update customize_table set agenda=?,cons_team_name=?,finish_bell=?,member_id=?,name=?,pros_team_name=?,used_at=?,warning_bell=? where id=? [40001-232]] [update customize_table set agenda=?,cons_team_name=?,finish_bell=?,member_id=?,name=?,pros_team_name=?,used_at=?,warning_bell=? where id=?]; SQL [update customize_table set agenda=?,cons_team_name=?,finish_bell=?,member_id=?,name=?,pros_team_name=?,used_at=?,warning_bell=? where id=?]
	at org.springframework.orm.jpa.vendor.HibernateJpaDialect.convertHibernateAccessException(HibernateJpaDialect.java:287)
	...
Caused by: org.hibernate.exception.LockAcquisitionException: could not execute statement [Deadlock detected. The current transaction was rolled back. Details: "CUSTOMIZE_TABLE"; SQL statement:
update customize_table set agenda=?,cons_team_name=?,finish_bell=?,member_id=?,name=?,pros_team_name=?,used_at=?,warning_bell=? where id=? [40001-232]] [update customize_table set agenda=?,cons_team_name=?,finish_bell=?,member_id=?,name=?,pros_team_name=?,used_at=?,warning_bell=? where id=?]
	at org.hibernate.dialect.H2Dialect.lambda$buildSQLExceptionConversionDelegate$3(H2Dialect.java:768)
	...
```
