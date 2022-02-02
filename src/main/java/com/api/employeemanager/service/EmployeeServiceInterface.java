package com.api.employeemanager.service;

import com.api.employeemanager.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeServiceInterface {
    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO);
    public List<EmployeeDTO> findAllEmployees();
    public EmployeeDTO updateEmployee(Long id,EmployeeDTO employeeDTO);
    public EmployeeDTO findEmployeeById(Long id);
    public void deleteEmployee(Long id);
}
