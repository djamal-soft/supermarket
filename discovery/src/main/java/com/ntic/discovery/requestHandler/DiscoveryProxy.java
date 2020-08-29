package com.ntic.discovery.requestHandler;


import com.ntic.discovery.entity.Microservice;
import com.ntic.discovery.enums.Urls;
import org.springframework.web.client.RestTemplate;

public class DiscoveryProxy {

    private RestTemplate rest;

    public DiscoveryProxy(RestTemplate rest) {
        this.rest = rest;
    }

    public DiscoveryProxy() {

        rest = new RestTemplate();
    }

    public Microservice getService(String key, float version) {

        String url = Urls.DISCOVERY_URL + key + "/" + version;
        return rest.getForObject(url, Microservice.class);
    }
}
