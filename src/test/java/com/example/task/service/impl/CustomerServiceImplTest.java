package com.example.task.service.impl;

import com.example.task.dto.CustomerDto;
import com.example.task.dto.request.CustomerRequest;
import com.example.task.model.Customer;
import com.example.task.service.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Initialize @Mock annotated fields
        customerService = new CustomerServiceImpl(customerRepository);
    }

    @Test
    public void testGetCustomerByCustomerID_ValidID_ShouldReturnDto() {
        // Arrange
        Long customerID = 1L;
        Customer customer = new Customer();
        customer.setFname("John");
        customer.setLname("Doe");
        customer.setUsername("johndoe");

        when(customerRepository.getCustomerByCustomerID(customerID)).thenReturn(customer);

        // Act
        CustomerDto customerDto = customerService.getCustomerByCustomerID(customerID);

        // Assert
        assertNotNull(customerDto);
        assertEquals("John", customerDto.getFname());
        assertEquals("Doe", customerDto.getLname());
        assertEquals("johndoe", customerDto.getUsername());
    }

    @Test
    public void testSaveCustomer_ValidRequest_ShouldSave() {
        // Arrange
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setFname("Jane");
        customerRequest.setLname("Smith");
        customerRequest.setUsername("janesmith");

        // Act
        customerService.saveCustomer(customerRequest);

        // Assert
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    public void testUpdateCustomer_ValidRequest_ShouldUpdate() {
        // Arrange
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setCustomerID(1L);
        customerRequest.setFname("UpdatedName");
        customerRequest.setLname("UpdatedLastName");
        customerRequest.setContactAdd("UpdatedAddress");
        customerRequest.setUsername("updatedusername");
        customerRequest.setPassword("updatedpassword");

        // Act
        customerService.updateCustomer(customerRequest);

        // Assert
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    public void testGetAll_ShouldReturnListOfDtos() {
        // Arrange
        List<Customer> customers = new ArrayList<>();
        Customer customer1 = new Customer();
        customer1.setFname("John");
        customer1.setLname("Doe");
        customer1.setUsername("johndoe");
        Customer customer2 = new Customer();
        customer2.setFname("Jane");
        customer2.setLname("Smith");
        customer2.setUsername("janesmith");
        customers.add(customer1);
        customers.add(customer2);

        when(customerRepository.findAll()).thenReturn(customers);

        // Act
        List<CustomerDto> customerDtos = customerService.getAll();

        // Assert
        assertNotNull(customerDtos);
        assertEquals(2, customerDtos.size());
        assertEquals("John", customerDtos.get(0).getFname());
        assertEquals("Jane", customerDtos.get(1).getFname());
    }

    @Test
    public void testDeleteByCustomerID_ExistingID_ShouldDelete() {
        // Arrange
        Long customerID = 1L;
        Customer customer = new Customer();
        customer.setCustomerID(customerID);

        when(customerRepository.getCustomerByCustomerID(customerID)).thenReturn(customer);

        // Act
        customerService.deleteByCustomerID(customerID);

        // Assert
        verify(customerRepository, times(1)).deleteById(customerID);
    }

    @Test
    public void testDeleteByCustomerID_NonExistingID_ShouldThrowException() {
        // Arrange
        Long customerID = 1L;
        when(customerRepository.getCustomerByCustomerID(customerID)).thenReturn(null);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> customerService.deleteByCustomerID(customerID));
        verify(customerRepository, never()).deleteById(customerID);
    }
}
