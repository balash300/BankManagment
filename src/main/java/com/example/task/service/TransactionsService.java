package com.example.task.service;

import com.example.task.dto.TransactionsDto;
import com.example.task.dto.request.TransactionsRequest;

import java.util.List;

public interface TransactionsService {

    TransactionsDto getTransactionsByTransID(Long transID);

    void saveTransactions(TransactionsRequest transactionsRequest);

    void updateTransactions(TransactionsRequest transactionsRequest);

    List<TransactionsDto> getAll();

    void deleteByTransactionsID(Long transactionsID);

}
