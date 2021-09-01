package com.employee.employeedata.Controller;

import com.employee.employeedata.Service.UserService;
import com.employee.employeedata.dto.UserRegistrationDto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
    private UserService userService;
    

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }
    @ModelAttribute("employee")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }
    @GetMapping
    public String showRegistratioForm(){
        return "registration";
    }

    @PostMapping
    public String EmployeeRegistration(@ModelAttribute("employee") UserRegistrationDto registrationDto){
        userService.save(registrationDto);
        return "redirect:/";
    }
}
