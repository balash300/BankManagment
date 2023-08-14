package com.example.task.service.repository;

import com.example.task.model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees, Long> {

    Employees getEmployeesByEmployeeID(Long employeeID);

}
