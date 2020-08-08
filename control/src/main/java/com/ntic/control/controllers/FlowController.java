package com.ntic.control.controllers;

import com.ntic.control.dto.AwaitedItemDto;
import com.ntic.control.dto.NewServiceDto;
import com.ntic.control.models.AwaitedItem;
import com.ntic.control.models.Microservice;
import com.ntic.control.observerPattren.IObserver;
import com.ntic.control.observerPattren.IPublisher;
import com.ntic.control.observerPattren.Observer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class FlowController {

    @Autowired
    private IPublisher publisher;


    @PostMapping(value = "/error")
    public void captureBreakdowns(@RequestBody AwaitedItemDto awaitedItem) {

        addToWaitingList(awaitedItem.getAwaited(), awaitedItem.getBrokenDown());
    }

    private void addToWaitingList(Microservice awaited, Microservice brokenDown) {
        AwaitedItem awaitedItem = new AwaitedItem(awaited, brokenDown);

         boolean isInList = publisher.isInList(awaitedItem);

         if(!isInList) {

             // add service to awaited services
             IObserver observer = new Observer(awaitedItem);
             publisher.attache(observer);

             // get new service instance from cloning service
             newServiceFromCloning(brokenDown);
         }
    }

    /**
     * listen a cloning service response
     * @param newService
     */
    @PostMapping(value = "/new-service")
    public void listeningResponseFromCloning(@RequestBody NewServiceDto newService) {

        publisher.notify(newService.getOldService(), newService.getNewService());
        notifyDiscoveryService(newService.getOldService(), newService.getNewService());
    }

    /**
     * send micro service to cloning for get new instance
     * @param brokenDown
     */
    private void newServiceFromCloning(Microservice brokenDown) {

        String cloningServiceUrl = "http://localhost:8092/new-instance";
        RestTemplate rest = new RestTemplate();
        HttpEntity<Microservice> request = new HttpEntity<>(brokenDown);
        rest.postForLocation(cloningServiceUrl, request);
    }

    /**
     * notify discovery service to replace old service by new service
     * @param oldService
     * @param newService
     */
    private void notifyDiscoveryService(Microservice oldService, Microservice newService) {
        String discoveryServiceUrl = "http://localhost:8090/replace";
        Microservice microservice = new Microservice();

        microservice.setId(oldService.getId());
        microservice.setAddress(newService.getAddress());
        microservice.setMkeys(newService.getMkeys());
        microservice.setVersion(newService.getVersion());

        RestTemplate rest = new RestTemplate();
        HttpEntity<Microservice> request = new HttpEntity<>(microservice);
        rest.put(discoveryServiceUrl, request);
    }
}
