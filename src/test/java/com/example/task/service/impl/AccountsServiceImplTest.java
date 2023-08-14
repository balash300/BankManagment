package com.example.task.service.impl;

import com.example.task.dto.AccountsDto;
import com.example.task.dto.request.AccountsRequest;
import com.example.task.model.Accounts;
import com.example.task.service.repository.AccountsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AccountsServiceImplTest {

    @Mock
    private AccountsRepository accountsRepository;

    @InjectMocks
    private AccountsServiceImpl accountsService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        accountsService = new AccountsServiceImpl(accountsRepository);
    }

    @Test
    public void testGetAccountsByAccID() {
        Long accID = 1L;
        Accounts accounts = new Accounts();
        accounts.setAccName("Test Account");

        when(accountsRepository.getAccountsByAccID(accID)).thenReturn(accounts);

        AccountsDto result = accountsService.getAccountsByAccID(accID);

        assertEquals("Test Account", result.getAccName());

        verify(accountsRepository, times(1)).getAccountsByAccID(accID);
        verifyNoMoreInteractions(accountsRepository);
    }

    @Test
    public void testSaveAccounts() {
        AccountsRequest accountsRequest = new AccountsRequest();
        accountsRequest.setAccName("New Account");

        accountsService.saveAccounts(accountsRequest);

        verify(accountsRepository, times(1)).save(any(Accounts.class));
        verifyNoMoreInteractions(accountsRepository);
    }

    @Test
    public void testUpdateAccounts() {
        AccountsRequest accountsRequest = new AccountsRequest();
        accountsRequest.setAccID(1L);
        accountsRequest.setCustomerID(1L);
        accountsRequest.setAccName("Updated Account");

        accountsService.updateAccounts(accountsRequest);

        verify(accountsRepository, times(1)).save(any(Accounts.class));
        verifyNoMoreInteractions(accountsRepository);
    }

    @Test
    public void testGetAll() {
        List<Accounts> accountsList = new ArrayList<>();
        Accounts account1 = new Accounts();
        account1.setAccName("Account 1");
        Accounts account2 = new Accounts();
        account2.setAccName("Account 2");
        accountsList.add(account1);
        accountsList.add(account2);

        when(accountsRepository.findAll()).thenReturn(accountsList);

        List<AccountsDto> result = accountsService.getAll();

        assertEquals(2, result.size());
        assertEquals("Account 1", result.get(0).getAccName());
        assertEquals("Account 2", result.get(1).getAccName());

        verify(accountsRepository, times(1)).findAll();
        verifyNoMoreInteractions(accountsRepository);
    }

    @Test
    public void testDeleteByAccID_ExistingAccID_ShouldDelete() {
        // Arrange
        Long accID = 1L;
        Accounts accounts = new Accounts();
        accounts.setAccID(accID);

        when(accountsRepository.getAccountsByAccID(accID)).thenReturn(accounts);

        // Act
        accountsService.deleteByAccID(accID);

        // Assert
        verify(accountsRepository, times(1)).deleteById(accID);
    }

    @Test
    public void testDeleteByAccID_NonExistingAccID_ShouldThrowException() {
        // Arrange
        Long accID = 1L;

        when(accountsRepository.getAccountsByAccID(accID)).thenReturn(null);

        // Act & Assert
        try {
            accountsService.deleteByAccID(accID);
            // If the exception is not thrown, fail the test
            fail("Expected RuntimeException but got none.");
        } catch (RuntimeException e) {
            // Expected behavior
        }

        verify(accountsRepository, never()).deleteById(accID);
    }


}
