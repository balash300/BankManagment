package com.example.task.service;

import com.example.task.dto.AccountsDto;
import com.example.task.dto.request.AccountsRequest;

import java.util.List;

public interface AccountsService {

    AccountsDto getAccountsByAccID(Long accID);

    void saveAccounts(AccountsRequest accountsRequest);

    void updateAccounts(AccountsRequest accountsRequest);

    List<AccountsDto> getAll();

    void deleteByAccID(Long accID);

}
