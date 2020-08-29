package com.ntic.control.controllers;

import com.ntic.control.models.Microservice;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;

@Component
public class AwaitedListController {

    public static ArrayList<Microservice> microservices = new ArrayList<>();

    public boolean checkMicroserviceExist(Microservice microservice) {
        for (Microservice ms: microservices) {
            if (microservice.getId() == ms.getId()) {
                return true;
            }
        }
        return false;
    }

    public void addMicroservice(Microservice microservice) {
        if(!checkMicroserviceExist(microservice)) {
            microservices.add(microservice);
        }
    }

    public void deleteMicroservice(Microservice microservice) {

        for (Iterator<Microservice> iterator = microservices.iterator(); iterator.hasNext(); ) {
            Microservice ms = iterator.next();
            if (ms.getId() == microservice.getId()) {
                iterator.remove();
            }
        }

    }
}
