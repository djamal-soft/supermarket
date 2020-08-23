package com.supermarket.employees.controllers;


import com.supermarket.employees.dao.EmployeeDao;
import com.supermarket.employees.exceptions.EmployeeNotFoundException;
import com.supermarket.employees.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeDao dao;

    @GetMapping(value = "employees")
    public List<Employee> index() {

        return dao.findAll();
    }

    @GetMapping(value = "all-delivery-men")
    public List<Employee> allDeliveryMen() {

        return dao.findAllByRole("DELIVERY_MAN");
    }

    @GetMapping(value = "employees/{id}")
    public Employee show(@PathVariable("id") int id) {

        Employee employee = dao.findById(id);

        if (employee == null) throw new EmployeeNotFoundException();

        return employee;
    }

    @GetMapping(value = "employee-by-email/{email}")
    public Employee show(@PathVariable("email") String email) {

        Employee employee = dao.findByEmail(email);

        if (employee == null) throw new EmployeeNotFoundException();

        return employee;
    }

    @PostMapping(value = "employees")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee store(@Valid @RequestBody Employee employee) {

        return dao.save(employee);
    }

    @PutMapping(value = "employees/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee update(@RequestBody Employee employee) {

        return dao.save(employee);
    }

    @DeleteMapping(value = "employees/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable("id") int id) {
        try {
            dao.deleteById(id);
        } catch (Exception e) {
            ;
        }
    }
}
