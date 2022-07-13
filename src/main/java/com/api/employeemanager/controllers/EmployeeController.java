package com.api.employeemanager.controllers;

import com.api.employeemanager.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.api.employeemanager.dto.EmployeeDTO;
import com.api.employeemanager.service.EmployeeService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employee")
@Validated
@CrossOrigin(maxAge = 3600)
public class EmployeeController {
    private final EmployeeService employeeService;
    @Autowired
    private Environment environment;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public ResponseEntity<?> hello(){
        return ResponseEntity.ok("Hello from CRUD application");
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees () throws CustomException {
        List<EmployeeDTO> employeeDTOList = employeeService.findAllEmployees();
        return new ResponseEntity<>(employeeDTOList, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById (
            @PathVariable("id") Long id) throws CustomException{
        EmployeeDTO employeeDTO = employeeService.findEmployeeById(id);
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<EmployeeDTO> addEmployee(@Valid @RequestBody EmployeeDTO employeeDTO)
            throws CustomException{
        EmployeeDTO newEmployeeDTO = employeeService.addEmployee(employeeDTO);
        return new ResponseEntity<>(newEmployeeDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(
            @PathVariable("id") Long id,
            @Valid @RequestBody EmployeeDTO employeeDTO) throws CustomException{
        EmployeeDTO updateEmployeeDTO = employeeService.updateEmployee(id,employeeDTO);
        return new ResponseEntity<>(updateEmployeeDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id) throws CustomException{
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Deleted SuccessFully with id: " + id);
    }

}
