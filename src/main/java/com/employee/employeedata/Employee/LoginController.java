package com.employee.employeedata.Employee;

import java.util.Arrays;

import com.employee.employeedata.Service.UserService;
import com.employee.employeedata.dto.UserRegistrationDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("listEmployees", employeeService.getAllEmployees() );
        return "index";
    }
    @GetMapping("/updateForm/{id}")
    public ModelAndView showForm(@PathVariable (value = "id") String id, Model model) {
        Employee employee = employeeService.getEmployee(id);
        Employee emp = new Employee(employee.getId(),employee.getName(),employee.getDesignation(), passwordEncoder.encode(employee.getPassword()),Arrays.asList(new Role("ROLE_USER")));
        model.addAttribute("employee", emp);
        ModelAndView obj = new ModelAndView("update_employee");
        return obj;
    }
    
    @PostMapping("/saveEmployee")
    public ModelAndView saveEmployee(@ModelAttribute("employee") Employee employee) {
    
        employeeService.addEmployee(employee);
        ModelAndView obj = new ModelAndView("redirect:/");
        return obj;
    }
    @GetMapping("/deleteEmployee/{id}")
    public ModelAndView deleteEmployee(@PathVariable (value="id") String id) {
        this.employeeService.deleteEmployee(id);
        ModelAndView obj = new ModelAndView("redirect:/");
        return obj;
    }

   
    
    
    
    
    
}
