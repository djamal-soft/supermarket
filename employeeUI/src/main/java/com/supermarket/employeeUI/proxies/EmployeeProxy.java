package com.supermarket.employeeUI.proxies;

import com.supermarket.employeeUI.models.Employee;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeProxy {


    public List<Employee> getAllEmployees() {
        RequestHandler handler = new RequestHandler();

        handler.setServiceKey("employee-management")
                .setServiceVersion(-1)
                .setMethod(RequestHandler.GET)
                .setResponseType(List.class);

        return (List<Employee>) handler.handle();
    }

    public Employee addEmployee(Employee employee) {
        RequestHandler handler = new RequestHandler();
        HttpEntity<Employee> request = new HttpEntity<>(employee);
        handler.setServiceKey("employee-management")
                .setServiceVersion(-1)
                .setMethod(RequestHandler.POST)
                .setResponseType(Employee.class)
                .setRequest(request);

        return (Employee) handler.handle();
    }

    public void deleteEmployee(int id) {
        RequestHandler handler = new RequestHandler();

        handler.setServiceKey("employee-management")
                .setServiceVersion(-1)
                .setMethod(RequestHandler.DELETE)
                .setAdditionnelParamsToUrl(id+"")
                .handle();
    }

    public Employee getEmployee(int id) {
        RequestHandler handler = new RequestHandler();

        handler.setServiceKey("employee-management")
                .setServiceVersion(-1)
                .setMethod(RequestHandler.GET)
                .setResponseType(Employee.class)
                .setAdditionnelParamsToUrl(id+"");

        return (Employee) handler.handle();
    }
}
