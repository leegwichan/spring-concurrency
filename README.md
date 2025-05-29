# spring-concurrency

&nbsp; 다음 레포지토리는 [토론 타이머 프로젝트](https://github.com/debate-timer)에서 발생했던 동시성 문제를 탐구하고 해결했던 과정을 담았다.
테스트를 통해 문제 상황과 해결 상황을 제시하였다.

### 문제 상황 & 해결 과정

| 버전 | 설명                       | 문서                               | 서비스 코드                                                                                              | 테스트 코드                                                                                                      |
|----|--------------------------|----------------------------------|-----------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------|
| v0 | 동시성 문제 발생한 코드 설명         | [ServiceV0.md](./docs/ServiceV0) | [CustomizeTableServiceV0](./src/main/java/concurrency/demo/service/v0/CustomizeTableServiceV1.java) | [CustomizeTableServiceV0Test](./src/test/java/concurrency/demo/service/v0/CustomizeTableServiceV0Test.java) |
| v1 | 트랜잭션 격리 수준 조정            | [ServiceV1.md](./docs/ServiceV1) | [CustomizeTableServiceV1](./src/main/java/concurrency/demo/service/v1/CustomizeTableServiceV2.java) | [CustomizeTableServiceV1Test](./src/test/java/concurrency/demo/service/v1/CustomizeTableServiceV1Test.java) |
| v2 | 부모 객체의 비관적 락 이용          | [ServiceV2.md](./docs/ServiceV2) | [CustomizeTableServiceV2](./src/main/java/concurrency/demo/service/v2/CustomizeTableServiceV3.java) | [CustomizeTableServiceV2Test](./src/test/java/concurrency/demo/service/v2/CustomizeTableServiceV2Test.java) |
| v3 | 조회&수정 로직을 조회 없이 수정하도록 변경 | [ServiceV3.md](./docs/ServiceV3) | [CustomizeTableServiceV3](./src/main/java/concurrency/demo/service/v3/CustomizeTableServiceV4.java) | [CustomizeTableServiceV3Test](./src/test/java/concurrency/demo/service/v3/CustomizeTableServiceV3Test.java) |
