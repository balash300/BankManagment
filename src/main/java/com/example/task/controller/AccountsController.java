package com.example.task.controller;

import com.example.task.dto.AccountsDto;
import com.example.task.dto.request.AccountsRequest;
import com.example.task.model.Accounts;
import com.example.task.service.AccountsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/accounts")
public class AccountsController {

    final AccountsService accountsService;

    @GetMapping(value = "/accountList")
    public List<AccountsDto> getAccountInfo(){
        return accountsService.getAll();
    }

    @GetMapping(value = "/getAccountsByAccID/{accID}")
    public AccountsDto getAccountsByAccID(@PathVariable(value = "accID") Long accID){
        return accountsService.getAccountsByAccID(accID);
    }

    @PostMapping(value = "/saveAccounts")
    public void saveAccounts(@RequestBody AccountsRequest accountsRequest){
        accountsService.saveAccounts(accountsRequest);
    }

    @PostMapping(value = "/updateAccounts")
    public void updateAccounts(@RequestBody AccountsRequest accountsRequest){
        accountsService.updateAccounts(accountsRequest);
    }

    @DeleteMapping(value = "/deleteByAccID/{accID}")
    public void deleteByAccID(@PathVariable(value = "accID") Long accID) {
        accountsService.deleteByAccID(accID);
    }
}
