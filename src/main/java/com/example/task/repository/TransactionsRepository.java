package com.example.task.service.repository;

import com.example.task.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long> {

    Transactions getTransactionsByTransID(Long transID);

}
