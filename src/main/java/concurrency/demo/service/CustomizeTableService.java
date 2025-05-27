package concurrency.demo.service;

import concurrency.demo.dto.CustomizeTableCreateRequest;

public interface CustomizeTableService {

    void updateTable(CustomizeTableCreateRequest tableCreateRequest, long tableId, long memberId);
}
