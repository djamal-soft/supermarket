package com.supermarket.orders.proxies;



import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.supermarket.orders.models.Employee;
import com.supermarket.orders.requestHandler.RequestHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeProxy {


    public List<Employee> getAllDeliveryMen() {
        RequestHandler handler = new RequestHandler();
        ObjectMapper mapper = new ObjectMapper();
        handler.setServiceKey("all-delivery-men")
                .setServiceVersion(-1)
                .setMethod(RequestHandler.GET)
                .setResponseType(List.class);

        List<Employee> employees = (List<Employee>) handler.handle();
        return mapper.convertValue(
                employees,
                new TypeReference<List<Employee>>() {
                }
        );
    }
}
