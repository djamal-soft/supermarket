package com.supermarket.employeeUI.controllers;

import com.supermarket.employeeUI.models.Employee;
import com.supermarket.employeeUI.proxies.EmployeeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeProxy employeeProxy;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping(value = "/employees")
    public String allEmployees(Model model) {

        List<Employee> employees = employeeProxy.getAllEmployees();
        model.addAttribute("employees", employees);

        return "pages/admin/employees/employees";
    }

    @GetMapping(value = "/employees/add")
    public String addEmployeeView(Model model) {

        Employee employee = new Employee();
        model.addAttribute("employee", employee);

        return "pages/admin/employees/add-employee";
    }

    @GetMapping(value = "/employees/update/{id}")
    public String updateEmployeeView(@PathVariable("id") int id, Model model) {

        Employee employee = employeeProxy.getEmployee(id);
        model.addAttribute("employee", employee);

        return "pages/admin/employees/add-employee";
    }

    @PostMapping(value = "employees")
    public String addEmployee(Employee employee) {

        employee.setPassword(
                passwordEncoder.encode(employee.getPassword())
        );
        employeeProxy.addEmployee(employee);

        return "redirect:/employees";
    }

    @DeleteMapping(value = "employees")
    public String deleteEmployee(@RequestParam("id") int id) {
        employeeProxy.deleteEmployee(id);

        return "redirect:/employees";
    }
}
