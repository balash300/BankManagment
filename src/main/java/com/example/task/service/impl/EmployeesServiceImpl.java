package com.example.task.service.impl;

import com.example.task.dto.EmployeesDto;
import com.example.task.dto.request.EmployeesRequest;
import com.example.task.model.Employees;
import com.example.task.service.repository.EmployeesRepository;
import com.example.task.service.EmployeesService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeesServiceImpl implements EmployeesService {

    private final EmployeesRepository employeesRepository;

    @Override
    public EmployeesDto getEmployeesByEmployeeID(Long employeeID) {
        Employees employees = employeesRepository.getEmployeesByEmployeeID(employeeID);
        return EmployeesDto.builder()
                .fname(employees.getFname())
                .lname(employees.getLname())
                .username(employees.getUsername())
                .build();
    }

    @Override
    @Transactional
    public void saveEmployees(EmployeesRequest employeesRequest) {
        Employees employees = Employees.builder()
                .fname(employeesRequest.getFname())
                .lname(employeesRequest.getLname())
                .username(employeesRequest.getUsername())
                .build();

        employeesRepository.save(employees);
    }

    @Override
    @Transactional
    public void updateEmployees(EmployeesRequest employeesRequest) {
        Employees employees = Employees.builder()
                .employeeID(employeesRequest.getEmployeeID())
                .fname(employeesRequest.getFname())
                .lname(employeesRequest.getLname())
                .address(employeesRequest.getAddress())
                .contactAdd(employeesRequest.getContactAdd())
                .username(employeesRequest.getUsername())
                .password(employeesRequest.getPassword())
                .build();

        employeesRepository.save(employees);
    }

    @Override
    public List<EmployeesDto> getAll() {
        List<Employees> employees = employeesRepository.findAll();
        List<EmployeesDto> employeesDtos = new ArrayList<>();

        for (Employees employee : employees) {
            EmployeesDto employeesDto = EmployeesDto.builder()
                    .fname(employee.getFname())
                    .lname(employee.getLname())
                    .username(employee.getUsername())
                    .build();

            employeesDtos.add(employeesDto);
        }

        return employeesDtos;
    }

    @Override
    public void deleteByEmployeeID(Long id) {

        Employees employees = employeesRepository.getEmployeesByEmployeeID(id);
        if (employees.getEmployeeID().equals(null)){
            throw new RuntimeException("There is no such information");
        } else {
            employeesRepository.deleteById(id);
        }

    }

}
