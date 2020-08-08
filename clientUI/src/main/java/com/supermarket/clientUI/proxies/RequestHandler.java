package com.supermarket.clientUI.proxies;

import com.supermarket.clientUI.models.Microservice;
import org.springframework.web.client.RestTemplate;

public class RequestHandler {

    private String serviceKey;
    private String additionnelParamsToUrl;
    private Class responseType;
    private float serviceVersion;
    private RestTemplate rest;

    public RequestHandler() {

        rest = new RestTemplate();
        additionnelParamsToUrl = null;
    }

    public RequestHandler setServiceKey(String serviceKey) {
        this.serviceKey = serviceKey;
        return this;
    }

    public RequestHandler setResponseType(Class responseType) {
        this.responseType = responseType;

        return this;
    }

    public RequestHandler setServiceVersion(float serviceVersion) {
        this.serviceVersion = serviceVersion;

        return this;
    }

    public RequestHandler setAdditionnelParamsToUrl(String param) {
        this.additionnelParamsToUrl = param;

        return this;
    }

    public Object handleGetRequest() {
        DiscoveryProxy discoveryProxy = new DiscoveryProxy();
        Microservice microservice = discoveryProxy.getService(serviceKey, serviceVersion);

        if(additionnelParamsToUrl != null) {
            microservice.setAddress(microservice.getAddress() + "/" + additionnelParamsToUrl);
        }

        Object response = null;
        try {
            response = rest.getForObject(microservice.getAddress(), responseType);
        }
        catch (Exception e) {

            System.out.println(e.getMessage());
            microservice = handleError(microservice);
            response = rest.getForObject(microservice.getAddress(), responseType);
        }


        return response;
    }

    private Microservice handleError(Microservice microservice) {

        return null;
    }
}
