package com.example.task.service.impl;

import com.example.task.dto.AccountsDto;
import com.example.task.dto.request.AccountsRequest;
import com.example.task.model.Accounts;
import com.example.task.service.repository.AccountsRepository;
import com.example.task.service.AccountsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountsServiceImpl implements AccountsService {

    private final AccountsRepository accountsRepository;

    @Override
    public AccountsDto getAccountsByAccID(Long accID) {
        Accounts accounts = accountsRepository.getAccountsByAccID(accID);
        return AccountsDto.builder()
                .accName(accounts.getAccName())
                .build();
    }

    @Override
    public void saveAccounts(AccountsRequest accountsRequest) {
        Accounts accounts = Accounts.builder()
                .accName(accountsRequest.getAccName())
                .build();

        accountsRepository.save(accounts);
    }

    @Override
    public void updateAccounts(AccountsRequest accountsRequest) {
        Accounts accounts = Accounts.builder()
                .accID(accountsRequest.getAccID())
                .costumerID(accountsRequest.getCustomerID())
                .accName(accountsRequest.getAccName())
                .build();

        accountsRepository.save(accounts);
    }

    @Override
    public List<AccountsDto> getAll() {
        List<Accounts> accounts = accountsRepository.findAll();
        List<AccountsDto> accountsDtos = new ArrayList<>();

        for (Accounts account : accounts) {
            AccountsDto accountsDto = AccountsDto.builder()
                    .accName(account.getAccName())
                    .build();

            accountsDtos.add(accountsDto);
        }

        return accountsDtos;
    }

    @Override
    public void deleteByAccID(Long accID) {

        Accounts accounts = accountsRepository.getAccountsByAccID(accID);
        if(accounts.getAccID().equals(null)) {
            throw new RuntimeException("");
        } else {
            accountsRepository.deleteById(accID);
        }

    }
}
