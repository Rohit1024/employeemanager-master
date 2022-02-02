package com.api.employeemanager.service;

import com.api.employeemanager.dto.EmployeeDTO;
import com.api.employeemanager.exception.CustomException;
import com.api.employeemanager.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.employeemanager.repo.EmployeeRepo;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeService implements EmployeeServiceInterface{
    private final EmployeeRepo employeeRepo;
    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
        Optional<Employee> optional = employeeRepo.findByEmail(employeeDTO.getEmail());
        if(optional.isPresent()) throw new CustomException("Service.EMAIL_EXIST");
        Employee employee = employeeRepo.dtoToEntity(employeeDTO);
        employeeRepo.save(employee);
        return employeeRepo.entityToDTO(employee);
    }

    public List<EmployeeDTO> findAllEmployees() {
        List<Employee> employeeList = employeeRepo.findAll();
        if (employeeList.isEmpty()) throw new CustomException("Service.NO_EMPLOYEES");
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        employeeList.forEach(emp -> {
            EmployeeDTO employeeDTO = employeeRepo.entityToDTO(emp);
            employeeDTOList.add(employeeDTO);
        });
        return employeeDTOList;
    }

    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Optional<Employee> optional = employeeRepo.findById(id);
        Employee employee = optional.orElseThrow(() -> new CustomException("Service.NOT_FOUND"));
        employee = employeeRepo.dtoToEntity(employeeDTO);
        employeeRepo.save(employee);
        return employeeRepo.entityToDTO(employee);
    }

    public EmployeeDTO findEmployeeById(Long id) {
        Optional<Employee> optional = employeeRepo.findById(id);
        Employee employee = optional.orElseThrow(() -> new CustomException("Service.NOT_FOUND"));
        return employeeRepo.entityToDTO(employee);
    }

    public void deleteEmployee(Long id){
        Optional<Employee> optional = employeeRepo.findEmployeeById(id);
        if (!optional.isPresent()) throw new CustomException("Service.NOT_FOUND");
        employeeRepo.deleteEmployeeById(id);
    }
}
