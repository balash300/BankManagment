package com.example.task.controller;

import com.example.task.dto.TransactionsDto;
import com.example.task.dto.request.TransactionsRequest;
import com.example.task.service.TransactionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/transactions")
public class TransactionsController {

    final TransactionsService transactionsService;

    @GetMapping(value = "/transactionList")
    public List<TransactionsDto> getTransactionsInfo() {
        return transactionsService.getAll();
    }

    @GetMapping(value = "/getTransactionsByTransID/{transID}")
    public TransactionsDto getTransactionsByTransID(@PathVariable(value = "transID") Long transID) {
        return transactionsService.getTransactionsByTransID(transID);
    }

    @PostMapping(value = "/saveTransactions")
    public void saveTransactions(@RequestBody TransactionsRequest transactionsRequest) {
        transactionsService.saveTransactions(transactionsRequest);
    }

    @PostMapping(value = "/updateTransactions")
    public void updateTransactions(@RequestBody TransactionsRequest transactionsRequest) {
        transactionsService.updateTransactions(transactionsRequest);
    }

    @DeleteMapping(value = "/deleteByTransactionsID/{transID}")
    public void deleteByTransactionsID(@PathVariable(value = "transID") Long transID) {
        transactionsService.deleteByTransactionsID(transID);
    }

}
