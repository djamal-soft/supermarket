package com.supermarket.orders.requestHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RequestHandler {

    public final static String GET    = "GET";
    public final static String POST   = "POST";
    public final static String PUT    = "PUT";
    public final static String DELETE = "DELETE";

    private String serviceKey;
    private String additionnelParamsToUrl; // use it for get method
    private Class responseType;
    private float serviceVersion;
    private HttpEntity request; // use it for post method
    private String method;
    private RestTemplate rest;

    @Autowired
    public RequestHandler() {

        rest = new RestTemplate();
        additionnelParamsToUrl = null;
        request = null;
        method = this.GET;
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

    public RequestHandler setRequest(HttpEntity request) {
        this.request = request;

        return this;
    }

    public RequestHandler setMethod(String method) {
        this.method = method;

        return this;
    }

    public Object handle() {
        DiscoveryProxy discoveryProxy = new DiscoveryProxy();
        Microservice microservice = discoveryProxy.getService(serviceKey, serviceVersion);

        if(additionnelParamsToUrl != null) {
            microservice.setAddress(microservice.getAddress() + "/" + additionnelParamsToUrl);
        }

        Object response = null;
        try {
            response = sendRequest(microservice);
        }
        catch (Exception e) {
            handleError();
        }

        return response;
    }

    private void handleError() {
        DiscoveryProxy discoveryProxy = new DiscoveryProxy();
        Microservice microservice = discoveryProxy.getService(serviceKey, serviceVersion);
        Microservice controleMicroservice = discoveryProxy.getService("notify-error", -1);

        HttpEntity<Microservice> request = new HttpEntity<>(microservice);

        try {
            rest.postForObject(controleMicroservice.getAddress(), request, Object.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private Object sendRequest(Microservice microservice) {
        Object response = null;

        switch (method) {
            case GET:
                response = rest.getForObject(microservice.getAddress(), responseType);
                break;
            case POST:
                response = rest.postForObject(microservice.getAddress(), request, responseType);
                break;
            case PUT:
//                rest.delete(microservice.getAddress());
                break;
            case DELETE:
                rest.delete(microservice.getAddress());
                break;
        }

        return response;
    }
}
