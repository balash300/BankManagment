package com.example.task.service.impl;

import com.example.task.dto.EmployeesDto;
import com.example.task.dto.request.EmployeesRequest;
import com.example.task.model.Employees;
import com.example.task.service.repository.EmployeesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EmployeesServiceImplTest {

    @Mock
    private EmployeesRepository employeesRepository;

    @InjectMocks
    private EmployeesServiceImpl employeesService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        employeesService = new EmployeesServiceImpl(employeesRepository);
    }

    @Test
    public void testGetEmployeesByEmployeeID_ValidID_ShouldReturnDto() {
        // Arrange
        Long employeeID = 1L;
        Employees employees = new Employees();
        employees.setFname("John");
        employees.setLname("Doe");
        employees.setUsername("johndoe");

        when(employeesRepository.getEmployeesByEmployeeID(employeeID)).thenReturn(employees);

        // Act
        EmployeesDto employeesDto = employeesService.getEmployeesByEmployeeID(employeeID);

        // Assert
        assertNotNull(employeesDto);
        assertEquals("John", employeesDto.getFname());
        assertEquals("Doe", employeesDto.getLname());
        assertEquals("johndoe", employeesDto.getUsername());
    }

    @Test
    public void testSaveEmployees_ValidRequest_ShouldSave() {
        // Arrange
        EmployeesRequest employeesRequest = new EmployeesRequest();
        employeesRequest.setFname("Jane");
        employeesRequest.setLname("Smith");
        employeesRequest.setUsername("janesmith");

        // Act
        employeesService.saveEmployees(employeesRequest);

        // Assert
        verify(employeesRepository, times(1)).save(any(Employees.class));
    }

    @Test
    public void testUpdateEmployees_ValidRequest_ShouldUpdate() {
        // Arrange
        EmployeesRequest employeesRequest = new EmployeesRequest();
        employeesRequest.setEmployeeID(1L);
        employeesRequest.setFname("UpdatedName");
        employeesRequest.setLname("UpdatedLastName");
        employeesRequest.setAddress("UpdatedAddress");
        employeesRequest.setContactAdd("UpdatedContactAdd");
        employeesRequest.setUsername("updatedusername");
        employeesRequest.setPassword("updatedpassword");

        // Act
        employeesService.updateEmployees(employeesRequest);

        // Assert
        verify(employeesRepository, times(1)).save(any(Employees.class));
    }

    @Test
    public void testGetAll_ShouldReturnListOfDtos() {
        // Arrange
        List<Employees> employeesList = new ArrayList<>();
        Employees employee1 = new Employees();
        employee1.setFname("John");
        employee1.setLname("Doe");
        employee1.setUsername("johndoe");
        Employees employee2 = new Employees();
        employee2.setFname("Jane");
        employee2.setLname("Smith");
        employee2.setUsername("janesmith");
        employeesList.add(employee1);
        employeesList.add(employee2);

        when(employeesRepository.findAll()).thenReturn(employeesList);

        // Act
        List<EmployeesDto> employeesDtos = employeesService.getAll();

        // Assert
        assertNotNull(employeesDtos);
        assertEquals(2, employeesDtos.size());
        assertEquals("John", employeesDtos.get(0).getFname());
        assertEquals("Jane", employeesDtos.get(1).getFname());
    }

    @Test
    public void testDeleteByEmployeeID_ExistingID_ShouldDelete() {
        // Arrange
        Long employeeID = 1L;
        Employees employees = new Employees();
        employees.setEmployeeID(employeeID);

        when(employeesRepository.getEmployeesByEmployeeID(employeeID)).thenReturn(employees);

        // Act
        employeesService.deleteByEmployeeID(employeeID);

        // Assert
        verify(employeesRepository, times(1)).deleteById(employeeID);
    }

    @Test
    public void testDeleteByEmployeeID_NonExistingID_ShouldThrowException() {
        // Arrange
        Long employeeID = 1L;
        when(employeesRepository.getEmployeesByEmployeeID(employeeID)).thenReturn(null);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> employeesService.deleteByEmployeeID(employeeID));
        verify(employeesRepository, never()).deleteById(employeeID);
    }
}
