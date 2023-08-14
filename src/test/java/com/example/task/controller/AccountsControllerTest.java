package com.example.task.controller;

import com.example.task.dto.AccountsDto;
import com.example.task.dto.request.AccountsRequest;
import com.example.task.service.AccountsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class AccountsControllerTest {

    @Mock
    private AccountsService accountsService;

    @InjectMocks
    private AccountsController accountsController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(accountsController).build();
    }

    @Test
    public void testGetAccountInfo() {
        // Mocking the service response
        List<AccountsDto> mockAccountList = new ArrayList<>();
        mockAccountList.add(new AccountsDto("Balash"));
        when(accountsService.getAll()).thenReturn(mockAccountList);

        // Calling the controller method
        List<AccountsDto> accountList = accountsController.getAccountInfo();

        // Asserting the result
        assertEquals(1, accountList.size());
        assertEquals("Balash", accountList.get(0).getAccName());
    }

    @Test
    public void testGetAccountsByAccID() {
        // Mocking the service response
        AccountsDto mockAccount = new AccountsDto("Balash");
        when(accountsService.getAccountsByAccID(123L)).thenReturn(mockAccount);

        // Calling the controller method
        AccountsDto account = accountsController.getAccountsByAccID(123L);

        // Asserting the result
        assertEquals("Balash", account.getAccName());
    }

    @Test
    public void testSaveAccounts() {
        // Creating a mock request
        AccountsRequest request = new AccountsRequest(1L,1L,"Balash");

        // Calling the controller method
        accountsController.saveAccounts(request);

        // Verifying that the service method was called with the correct arguments
        verify(accountsService).saveAccounts(request);
    }

    @Test
    public void testUpdateAccounts() {
        // Creating a mock request
        AccountsRequest request = new AccountsRequest(1L, 1L,"Balash");

        // Calling the controller method
        accountsController.updateAccounts(request);

        // Verifying that the service method was called with the correct arguments
        verify(accountsService).updateAccounts(request);
    }

    @Test
    public void testDeleteByAccID() throws Exception {
        Long accID = 1L;

        mockMvc.perform(delete("/accounts/deleteByAccID/" + accID))
                .andExpect(status().isOk());

        verify(accountsService, times(1)).deleteByAccID(accID);
    }

}