package com.example.task.service;

import com.example.task.dto.CustomerDto;
import com.example.task.dto.request.CustomerRequest;

import java.util.List;

public interface CustomerService {

    CustomerDto getCustomerByCustomerID(Long customerID);

    void saveCustomer(CustomerRequest customerRequest);

    void updateCustomer(CustomerRequest customerRequest);

    List<CustomerDto> getAll();

    void deleteByCustomerID(Long customerID);

}
