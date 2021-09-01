package com.employee.employeedata.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import com.employee.employeedata.Employee.Employee;
import com.employee.employeedata.Employee.EmployeeRepository;
import com.employee.employeedata.Employee.Role;
import com.employee.employeedata.dto.UserRegistrationDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    public UserServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee save(UserRegistrationDto registrationDto) {
        Employee employee = new Employee(registrationDto.getId(), registrationDto.getName(), registrationDto.getDesignation(),
                                        passwordEncoder.encode(registrationDto.getPassword()), Arrays.asList(new Role("ROLE_USER")));
        return employeeRepository.save(employee);
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee == null){
            throw new UsernameNotFoundException("Invalid Employee ID or password");
        }
        return new org.springframework.security.core.userdetails.User(employee.get().getId(), employee.get().getPassword(), mapRolesToAuthorities(employee.get().getRoles()));
        
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
    
}
