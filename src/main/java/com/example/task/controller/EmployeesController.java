package com.example.task.controller;

import com.example.task.dto.EmployeesDto;
import com.example.task.dto.request.EmployeesRequest;
import com.example.task.service.EmployeesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/employees")
public class EmployeesController {

    final EmployeesService employeesService;

    @GetMapping(value = "/employeesList")
    public List<EmployeesDto> getEmplyeesInfo() {
        return employeesService.getAll();
    }

    @GetMapping(value = "/getEmployeesByEmployeeID/{employeeID}")
    public EmployeesDto getEmployeesByEmployeeID(@PathVariable(value = "employeeID") Long employeeID) {
        return employeesService.getEmployeesByEmployeeID(employeeID);
    }

    @PostMapping(value = "/saveEmployees")
    public void saveEmployees(@RequestBody EmployeesRequest employeesRequest) {
        employeesService.saveEmployees(employeesRequest);
    }

    @PostMapping(value = "/updateEmployees")
    public void updateEmployees(@RequestBody EmployeesRequest employeesRequest) {
        employeesService.updateEmployees(employeesRequest);
    }

    @DeleteMapping(value = "/deleteByEmployeeID/{employeeID}")
    public void deleteByEmployeeID(@PathVariable(value = "employeeID") Long employeeID){
        employeesService.deleteByEmployeeID(employeeID);
    }
}
