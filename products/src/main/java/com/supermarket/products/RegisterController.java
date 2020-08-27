package com.supermarket.products;

import com.supermarket.products.models.Microservice;
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
        ms.setAddress(makeUrl(host, port, "products"));
        ms.setMkeys("product-management");
        ms.setVersion(1);
        ms.setName("products");
        services.add(ms);

        ms = new Microservice();
        ms.setAddress(makeUrl(host, port, "products/search"));
        ms.setMkeys("search-products");
        ms.setVersion(1);
        ms.setName("products");
        services.add(ms);

        ms = new Microservice();
        ms.setAddress(makeUrl(host, port, "category/products"));
        ms.setMkeys("category-products");
        ms.setVersion(1);
        ms.setName("products");
        services.add(ms);

        ms = new Microservice();
        ms.setAddress(makeUrl(host, port, "images"));
        ms.setMkeys("upload-product-image");
        ms.setVersion(1);
        ms.setName("products");
        services.add(ms);

        for (Microservice service : services) {
            rest.postForLocation(DISCOVERY_URL,service);
        }
    }

    private String makeUrl(String host, String port, String path) {

        return "http://" + host + ":" + port + "/" + path;
    }
}
