package com.ntic.cloning.requestHandler;


import com.ntic.cloning.enums.Urls;
import com.ntic.cloning.models.Microservice;
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
