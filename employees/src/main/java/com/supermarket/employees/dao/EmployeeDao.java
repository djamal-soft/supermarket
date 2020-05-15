package com.supermarket.employees.dao;


import com.supermarket.employees.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer> {

    Employee findById(int id);
    void deleteById(int id);
}
