package com.example.task.controller;


import com.example.task.dto.CustomerDto;
import com.example.task.dto.request.CustomerRequest;
import com.example.task.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/customer")
public class CustomerController {

    final CustomerService customerService;

    @GetMapping(value = "/customerList")
    public List<CustomerDto> getCustomerInfo() {
        return customerService.getAll();
    }

    @GetMapping(value = "/getCustomerByCustomerID/{customerID}")
    public CustomerDto getCustomerByCustomerID(@PathVariable(value = "customerID") Long customerID) {
        return customerService.getCustomerByCustomerID(customerID);
    }

    @PostMapping(value = "/saveCustomer")
    public void saveCustomer(@RequestBody CustomerRequest customerRequest) {
        customerService.saveCustomer(customerRequest);
    }

    @PostMapping(value = "/updateCustomer")
    public void updateCustomer(@RequestBody CustomerRequest customerRequest) {
        customerService.updateCustomer(customerRequest);
    }

    @DeleteMapping(value = "/deleteByCustomerID/{customerID}")
    public void deleteByCustomerID(@PathVariable(value = "customerID") Long customerID) {
        customerService.deleteByCustomerID(customerID);
    }

}
