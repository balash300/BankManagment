package com.example.task.service.impl;

import com.example.task.dto.TransactionsDto;
import com.example.task.dto.request.TransactionsRequest;
import com.example.task.model.Transactions;
import com.example.task.service.repository.TransactionsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransactionsServiceImplTest {

    @Mock
    private TransactionsRepository transactionsRepository;

    @InjectMocks
    private TransactionsServiceImpl transactionsService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        transactionsService = new TransactionsServiceImpl(transactionsRepository);
    }

    @Test
    public void testGetTransactionsByTransID_ValidID_ShouldReturnDto() {
        // Arrange
        Long transID = 1L;
        Transactions transactions = new Transactions();
        transactions.setName("Purchase");

        when(transactionsRepository.getTransactionsByTransID(transID)).thenReturn(transactions);

        // Act
        TransactionsDto transactionsDto = transactionsService.getTransactionsByTransID(transID);

        // Assert
        assertNotNull(transactionsDto);
        assertEquals("Purchase", transactionsDto.getName());
    }

    @Test
    public void testSaveTransactions_ValidRequest_ShouldSave() {
        // Arrange
        TransactionsRequest transactionsRequest = new TransactionsRequest();
        transactionsRequest.setName("Refund");

        // Act
        transactionsService.saveTransactions(transactionsRequest);

        // Assert
        verify(transactionsRepository, times(1)).save(any(Transactions.class));
    }

    @Test
    public void testUpdateTransactions_ValidRequest_ShouldUpdate() {
        // Arrange
        TransactionsRequest transactionsRequest = new TransactionsRequest();
        transactionsRequest.setTransID(1L);
        transactionsRequest.setEmployeeID(2L);
        transactionsRequest.setCustomerID(3L);
        transactionsRequest.setName("Updated Transaction");

        // Act
        transactionsService.updateTransactions(transactionsRequest);

        // Assert
        verify(transactionsRepository, times(1)).save(any(Transactions.class));
    }

    @Test
    public void testGetAll_ShouldReturnListOfDtos() {
        // Arrange
        List<Transactions> transactionsList = new ArrayList<>();
        Transactions transaction1 = new Transactions();
        transaction1.setName("Purchase");
        Transactions transaction2 = new Transactions();
        transaction2.setName("Refund");
        transactionsList.add(transaction1);
        transactionsList.add(transaction2);

        when(transactionsRepository.findAll()).thenReturn(transactionsList);

        // Act
        List<TransactionsDto> transactionsDtos = transactionsService.getAll();

        // Assert
        assertNotNull(transactionsDtos);
        assertEquals(2, transactionsDtos.size());
        assertEquals("Purchase", transactionsDtos.get(0).getName());
        assertEquals("Refund", transactionsDtos.get(1).getName());
    }

    @Test
    public void testDeleteByTransactionsID_ExistingID_ShouldDelete() {
        // Arrange
        Long transID = 1L;
        Transactions transactions = new Transactions();
        transactions.setTransID(transID);

        when(transactionsRepository.getTransactionsByTransID(transID)).thenReturn(transactions);

        // Act
        transactionsService.deleteByTransactionsID(transID);

        // Assert
        verify(transactionsRepository, times(1)).deleteById(transID);
    }

    @Test
    public void testDeleteByTransactionsID_NonExistingID_ShouldThrowException() {
        // Arrange
        Long transactionsID = 1L;
        when(transactionsRepository.getTransactionsByTransID(transactionsID)).thenReturn(null);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> transactionsService.deleteByTransactionsID(transactionsID));
        verify(transactionsRepository, never()).deleteById(transactionsID);
    }
}
