package com.example.task.service.repository;

import com.example.task.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer getCustomerByCustomerID(Long customerID);

}
