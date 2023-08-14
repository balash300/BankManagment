package com.example.task.service.impl;

import com.example.task.dto.CustomerDto;
import com.example.task.dto.request.CustomerRequest;
import com.example.task.model.Customer;
import com.example.task.service.repository.CustomerRepository;
import com.example.task.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerDto getCustomerByCustomerID(Long customerID) {
        Customer customer = customerRepository.getCustomerByCustomerID(customerID);
        return CustomerDto.builder()
                .fname(customer.getFname())
                .lname(customer.getLname())
                .username(customer.getUsername())
                .build();
    }

    @Override
    public void saveCustomer(CustomerRequest customerRequest) {
        customerRepository.save(
                Customer.builder()
                .fname(customerRequest.getFname())
                .lname(customerRequest.getLname())
                .username(customerRequest.getUsername())
                .build());


    }

    @Override
    public void updateCustomer(CustomerRequest customerRequest) {
        Customer customer = Customer.builder()
                .customerID(customerRequest.getCustomerID())
                .fname(customerRequest.getFname())
                .lname(customerRequest.getLname())
                .address(customerRequest.getContactAdd())
                .username(customerRequest.getUsername())
                .password(customerRequest.getPassword())
                .build();

        customerRepository.save(customer);
    }

    @Override
    public List<CustomerDto> getAll() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDto> customerDtos = new ArrayList<>();

        for (Customer customer : customers) {
            CustomerDto customerDto = CustomerDto.builder()
                    .fname(customer.getFname())
                    .lname(customer.getLname())
                    .username(customer.getUsername())
                    .build();

            customerDtos.add(customerDto);
        }

        return customerDtos;
    }

    @Override
    public void deleteByCustomerID(Long customerID) {

        Customer customer = customerRepository.getCustomerByCustomerID(customerID);
        if (customer.getCustomerID().equals(null)) {
            throw new RuntimeException("There is no such information");
        } else {
            customerRepository.deleteById(customerID);
        }

    }
}
