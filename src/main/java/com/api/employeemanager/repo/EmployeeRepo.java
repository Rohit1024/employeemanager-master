package com.api.employeemanager.repo;

import com.api.employeemanager.dto.EmployeeDTO;
import com.api.employeemanager.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    void deleteEmployeeById(Long id);
    Optional<Employee> findByEmail(String email);
    Optional<Employee> findEmployeeById(Long id);

    public default EmployeeDTO entityToDTO(Employee employee){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setPhone(employee.getPhone());
        employeeDTO.setJobTitle(employee.getJobTitle());
        employeeDTO.setImageUrl(employee.getImageUrl());
        return employeeDTO;
    }

    public default Employee dtoToEntity(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPhone(employeeDTO.getPhone());
        employee.setJobTitle(employeeDTO.getJobTitle());
        employee.setImageUrl(employeeDTO.getImageUrl());
        return employee;
    }
}
