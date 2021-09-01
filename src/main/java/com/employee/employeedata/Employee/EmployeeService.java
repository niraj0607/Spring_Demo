package com.employee.employeedata.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    
public List<Employee> getAllEmployees(){
    List<Employee> employees = new ArrayList<>();
    employeeRepository.findAll()
    .forEach(employees::add);
    return employees;
}
public Employee getEmployee(String id){
    
    return employeeRepository.findById(id)
    .orElseThrow(() -> new ResourceNotFoundException("User not found with id : "+id));
}
public void addEmployee(Employee employee){
    employeeRepository.save(employee);
}
public void updateEmployee(Employee employee, String id) {
    Employee existing = employeeRepository.findById(id)
    .orElseThrow(() -> new ResourceNotFoundException("User not found with id : "+id));
    existing.setName(employee.getName());
    existing.setDesignation(employee.getDesignation());
    existing.setPassword(employee.getPassword());
    employeeRepository.save(existing);
}
public void deleteEmployee(String id){
    Employee existing = employeeRepository.findById(id)
    .orElseThrow(() -> new ResourceNotFoundException("User not found with id : "+id));
    employeeRepository.delete(existing);
}



}
