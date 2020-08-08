package com.ntic.control.observerPattren;

import com.ntic.control.models.AwaitedItem;
import com.ntic.control.models.Microservice;

public interface IPublisher {

    void attache(IObserver observer);
    void dettache(IObserver observer);
    void notify(Microservice oldService, Microservice newService);
    boolean isInList(AwaitedItem awaitedItem);
}
