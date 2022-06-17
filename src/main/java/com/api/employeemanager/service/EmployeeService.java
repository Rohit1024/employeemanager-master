package com.api.employeemanager.service;

import com.api.employeemanager.dto.EmployeeDTO;
import com.api.employeemanager.exception.CustomException;
import com.api.employeemanager.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
    private final Environment environment;
    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo, Environment environment) {
        this.employeeRepo = employeeRepo;
        this.environment = environment;
    }

    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) throws CustomException {
        Optional<Employee> optional = employeeRepo.findByEmail(employeeDTO.getEmail());
        if(optional.isPresent()) throw new CustomException("Service.EMAIL_EXIST");
        Employee employee = employeeRepo.dtoToEntity(employeeDTO);
        employeeRepo.save(employee);
        return employeeRepo.entityToDTO(employee);
    }

    public List<EmployeeDTO> findAllEmployees() throws CustomException{
        List<Employee> employeeList = employeeRepo.findAll();
        if (employeeList.isEmpty())
            throw new CustomException(environment.getProperty("Service.NO_EMPLOYEES"));
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        employeeList.forEach(emp -> {
            EmployeeDTO employeeDTO = employeeRepo.entityToDTO(emp);
            employeeDTOList.add(employeeDTO);
        });
        return employeeDTOList;
    }

    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) throws CustomException{
        Optional<Employee> optional = employeeRepo.findById(id);
        Employee employee = optional.orElseThrow(() ->
                new CustomException(environment.getProperty("Service.NOT_FOUND")));
        employee = employeeRepo.dtoToEntity(employeeDTO);
        employeeRepo.save(employee);
        return employeeRepo.entityToDTO(employee);
    }

    public EmployeeDTO findEmployeeById(Long id) throws CustomException{
        Optional<Employee> optional = employeeRepo.findById(id);
        Employee employee = optional.orElseThrow(() ->
                new CustomException(environment.getProperty("Service.NOT_FOUND")));
        return employeeRepo.entityToDTO(employee);
    }

    public void deleteEmployee(Long id) throws CustomException{
        Optional<Employee> optional = employeeRepo.findEmployeeById(id);
        if (!optional.isPresent())
            throw new CustomException(environment.getProperty("Service.NOT_FOUND"));
        employeeRepo.deleteEmployeeById(id);
    }
}
