package com.example.task.service.impl;

import com.example.task.dto.TransactionsDto;
import com.example.task.dto.request.TransactionsRequest;
import com.example.task.model.Transactions;
import com.example.task.service.repository.TransactionsRepository;
import com.example.task.service.TransactionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionsServiceImpl implements TransactionsService {

    private final TransactionsRepository transactionsRepository;

    @Override
    public TransactionsDto getTransactionsByTransID(Long transID) {
        Transactions transactions = transactionsRepository.getTransactionsByTransID(transID);
        return TransactionsDto.builder()
                .name(transactions.getName())
                .build();
    }

    @Override
    public void saveTransactions(TransactionsRequest transactionsRequest) {
        Transactions transactions = Transactions.builder()
                .name(transactionsRequest.getName())
                .build();

        transactionsRepository.save(transactions);
    }

    @Override
    public void updateTransactions(TransactionsRequest transactionsRequest) {
        Transactions transactions = Transactions.builder()
                .transID(transactionsRequest.getTransID())
                .employeeID(transactionsRequest.getEmployeeID())
                .customerID(transactionsRequest.getCustomerID())
                .name(transactionsRequest.getName())
                .build();

        transactionsRepository.save(transactions);
    }

    @Override
    public List<TransactionsDto> getAll() {
        List<Transactions> transactions = transactionsRepository.findAll();
        List<TransactionsDto> transactionsDtos = new ArrayList<>();

        for (Transactions transaction : transactions) {
            TransactionsDto transactionsDto = TransactionsDto.builder()
                    .name(transaction.getName())
                    .build();

            transactionsDtos.add(transactionsDto);
        }

        return transactionsDtos;
    }

    @Override
    public void deleteByTransactionsID(Long transactionsID) {

        Transactions transactions = transactionsRepository.getTransactionsByTransID(transactionsID);
        if (transactions.getTransID().equals(null)) {
            throw new RuntimeException("There is no such information");
        } else {
            transactionsRepository.deleteById(transactionsID);
        }

    }
}
