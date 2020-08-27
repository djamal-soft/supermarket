package com.ntic.control.controllers.observerPattren;

import com.ntic.control.models.AwaitedItem;
import com.ntic.control.models.Microservice;

public interface IObserver {

    void update(Microservice newService);
    void notifyDiscoveryService(Microservice oldService, Microservice newService);
    boolean canNotify(Microservice oldService);
    AwaitedItem getAwaitedItem();
}
