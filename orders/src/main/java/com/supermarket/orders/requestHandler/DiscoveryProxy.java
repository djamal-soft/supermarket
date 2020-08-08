package com.supermarket.orders.requestHandler;

import org.springframework.web.client.RestTemplate;

public class DiscoveryProxy {

    private static final String DISCOVERY_URL = "http://localhost:8090/discovery/";
    private RestTemplate rest;

    public DiscoveryProxy(RestTemplate rest) {
        this.rest = rest;
    }

    public DiscoveryProxy() {

        rest = new RestTemplate();
    }

    public Microservice getService(String key, float version) {

        String url = DISCOVERY_URL + key + "/" + version;
        return rest.getForObject(url, Microservice.class);
    }
}
