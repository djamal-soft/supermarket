package com.ntic.control.observerPattren;

import com.ntic.control.models.AwaitedItem;
import com.ntic.control.models.Microservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

public class Observer implements IObserver {

    private AwaitedItem awaitedItem;

    private RestTemplate rest;

    public Observer() {
        rest = new RestTemplate();
    }

    public Observer(AwaitedItem awaitedItem) {
        this.awaitedItem = awaitedItem;
        rest = new RestTemplate();
    }

    @Override
    public void update(Microservice newService) {
        HttpEntity<Microservice> request = new HttpEntity<>(newService);
//        rest.postForLocation(awaitedItem.getAwaited().getAddress(), request);
    }

    @Override
    public void notifyDiscoveryService(Microservice oldService, Microservice newService) {

    }

    @Override
    public boolean canNotify(Microservice oldService) {
        return oldService.getId() == awaitedItem.getBrokenDown().getId();
    }

    @Override
    public AwaitedItem getAwaitedItem() {
        return awaitedItem;
    }

    public void setAwaitedItem(AwaitedItem awaitedItem) {
        this.awaitedItem = awaitedItem;
    }
}
