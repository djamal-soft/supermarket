package com.ntic.control.controllers;

import com.ntic.control.enums.MicroserviceStatus;
import com.ntic.control.models.Microservice;
import com.ntic.control.requestHandler.RequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FlowController {

    @Autowired
    AwaitedListController awaitedListController;


    @PostMapping(value = "/error")
    public void captureBreakdowns(@RequestBody Microservice brokenDown) {

        System.out.println(AwaitedListController.microservices);
        // get new service instance from cloning service
        // if it is not in awaited list
        if(! awaitedListController.checkMicroserviceExist(brokenDown)) {

            // add microservice to awaited list
            awaitedListController.addMicroservice(brokenDown);

            // duplicate service using cloning microservice
             newServiceFromCloning(brokenDown);

            //change status of microservice in discovery microservice
            notifyDiscoveryService(brokenDown);
        }

    }

    /**
     * send micro service to cloning for get new instance
     * @param brokenDown
     */
    private void newServiceFromCloning(Microservice brokenDown) {

        RequestHandler handler = new RequestHandler();
        HttpEntity<Microservice> request = new HttpEntity<>(brokenDown);
        handler.setServiceKey("duplicate-service")
                .setServiceVersion(-1)
                .setRequest(request)
                .setMethod(RequestHandler.POST)
                .setResponseType(Object.class)
                .handle();
    }

    /**
     * notify discovery service to replace old service by new service
     * @param microservice
     */
    private void notifyDiscoveryService(Microservice microservice) {

        microservice.setStatus(MicroserviceStatus.BROKEN_DOWN);

        RequestHandler handler = new RequestHandler();
        HttpEntity<Microservice> request = new HttpEntity<>(microservice);
        handler.setServiceKey("replace-service")
                .setServiceVersion(-1)
                .setRequest(request)
                .setMethod(RequestHandler.POST)
                .setResponseType(Object.class)
                .handle();
    }

    /**
     * notify this microservice after duplicate microservice
     * @param brokenDown
     */
    @PostMapping(value = "duplicated")
    public void duplicated(@RequestBody Microservice brokenDown) {

        awaitedListController.deleteMicroservice(brokenDown);
    }

}
