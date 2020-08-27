package com.ntic.control.controllers;

import com.ntic.control.dto.NewServiceDto;
import com.ntic.control.models.Microservice;
import com.ntic.control.controllers.observerPattren.IPublisher;
import com.ntic.control.requestHandler.RequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@RestController
public class FlowController {

    @Autowired
    private IPublisher publisher;

    @Autowired
    AwaitedListController awaitedListController;


    @PostMapping(value = "/error")
    public void captureBreakdowns(@RequestBody Microservice brokenDown) {


//        System.out.println(awaitedListController.microservices);
//        System.out.println(awaitedListController.checkMicroserviceExist(brokenDown));
//        addToWaitingList(brokenDown);
//        addToWaitingList(awaitedItem.getAwaited(), awaitedItem.getBrokenDown());
//        addToWaitingList(microservice);

        // get new service instance from cloning service
        // if it is not in awaited list
        if(! awaitedListController.checkMicroserviceExist(brokenDown)) {
            newServiceFromCloning(brokenDown);
        }

        awaitedListController.addMicroservice(brokenDown);

             // Delete service from discovery service
//             deleteServiceFromDiscovery(brokenDown);
    }



//    private void addToWaitingList(Microservice awaited, Microservice brokenDown) {
//        AwaitedItem awaitedItem = new AwaitedItem(awaited, brokenDown);
//
//         boolean isInList = publisher.isInList(awaitedItem);
//
//         if(!isInList) {
//
//             // add service to awaited services
//             IObserver observer = new Observer(awaitedItem);
//             publisher.attache(observer);
//
//             // get new service instance from cloning service
//             newServiceFromCloning(brokenDown);
//
//             // Delete service from discovery service
//             deleteServiceFromDiscovery(brokenDown);
//         }
//    }

    /**
     * Delete service from discovery service
     * @param brokenDown
     */
    private void deleteServiceFromDiscovery(Microservice brokenDown) {

        RequestHandler handler = new RequestHandler();
        HttpEntity<Microservice> request = new HttpEntity<>(brokenDown);
        handler.setServiceKey("replace-service")
                .setServiceVersion(-1)
                .setRequest(request)
                .setMethod(RequestHandler.PUT)
                .setResponseType(Object.class)
                .handle();
    }

    /**
     * listen a cloning service response
     * @param newService
     */
//    @PostMapping(value = "/new-service")
//    public void listeningResponseFromCloning(@RequestBody NewServiceDto newService) {
//
//        publisher.notify(newService.getOldService(), newService.getNewService());
//        notifyDiscoveryService(newService.getNewService());
//    }

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
     * @param newService
     */
    private void notifyDiscoveryService(Microservice newService) {

        RequestHandler handler = new RequestHandler();
        HttpEntity<Microservice> request = new HttpEntity<>(newService);
        handler.setServiceKey("register-service")
                .setServiceVersion(-1)
                .setRequest(request)
                .setMethod(RequestHandler.POST)
                .setResponseType(Object.class)
                .handle();
    }

    @PostMapping(value = "duplicated")
    public void duplicated(@RequestBody Microservice brokenDown) {
        awaitedListController.deleteMicroservice(brokenDown);
    }

}
