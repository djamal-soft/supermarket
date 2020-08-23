package com.supermarket.products.proxies;



import com.supermarket.products.enums.Urls;
import com.supermarket.products.models.Microservice;
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
