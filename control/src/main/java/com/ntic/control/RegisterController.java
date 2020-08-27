package com.ntic.control;

import com.ntic.control.models.Microservice;
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
        ms.setAddress(makeUrl(host, port, "error"));
        ms.setMkeys("notify-error");
        ms.setVersion(1);
        ms.setName("control");
        services.add(ms);

        ms = new Microservice();
        ms.setAddress(makeUrl(host, port, "new-service"));
        ms.setMkeys("service-duplicated");
        ms.setVersion(1);
        ms.setName("control");

        services.add(ms);
        ms = new Microservice();
        ms.setAddress(makeUrl(host, port, "duplicated"));
        ms.setMkeys("duplicated");
        ms.setVersion(1);
        ms.setName("control");
        services.add(ms);

        for (Microservice service : services) {
            rest.postForLocation(DISCOVERY_URL,service);
        }
    }

    private String makeUrl(String host, String port, String path) {

        return "http://" + host + ":" + port + "/" + path;
    }
}
