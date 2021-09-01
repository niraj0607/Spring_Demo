package com.employee.employeedata.Service;

import com.employee.employeedata.Employee.Employee;
import com.employee.employeedata.dto.UserRegistrationDto;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    Employee save(UserRegistrationDto registrationDto);
}
