package com.supermarket.employees;



import com.supermarket.employees.models.Microservice;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

public class RegisterController {

    private final static String DISCOVERY_URL = "http://localhost:8090/register";
    private RestTemplate rest;
    private ArrayList<Microservice> services;

    public RegisterController() {
        rest = new RestTemplate();
        services = new ArrayList<>();
    }

    public void registerServices(String host, String port) {


        Microservice ms = new Microservice();
        ms.setAddress(makeUrl(host, port, "employees"));
        ms.setMkeys("employee-management");
        ms.setVersion(1);
        services.add(ms);

        ms = new Microservice();
        ms.setAddress(makeUrl(host, port, "employee-by-email"));
        ms.setMkeys("employee-by-email");
        ms.setVersion(1);
        services.add(ms);

        ms = new Microservice();
        ms.setAddress(makeUrl(host, port, "all-delivery-men"));
        ms.setMkeys("all-delivery-men");
        ms.setVersion(1);
        services.add(ms);


        for (Microservice service : services) {
            rest.postForLocation(DISCOVERY_URL,service);
        }
    }

    private String makeUrl(String host, String port, String path) {

        return "http://" + host + ":" + port + "/" + path;
    }
}
