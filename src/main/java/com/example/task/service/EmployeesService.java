package com.example.task.service;

import com.example.task.dto.EmployeesDto;
import com.example.task.dto.request.EmployeesRequest;

import java.util.List;

public interface EmployeesService {

    EmployeesDto getEmployeesByEmployeeID(Long employeeID);

    void saveEmployees(EmployeesRequest employeesRequest);

    void updateEmployees(EmployeesRequest employeesRequest);

    List<EmployeesDto> getAll();

    void deleteByEmployeeID(Long employeeID);
}
