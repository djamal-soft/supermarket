package com.ntic.control.controllers.observerPattren;

import com.ntic.control.models.AwaitedItem;
import com.ntic.control.models.Microservice;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Publisher implements IPublisher {

    private ArrayList<IObserver> waitingItems;

    public Publisher() {
        waitingItems = new ArrayList<>();
    }

    @Override
    public void attache(IObserver observer) {
        waitingItems.add(observer);
    }

    @Override
    public void dettache(IObserver observer) {
        waitingItems.remove(observer);
    }

    @Override
    public void notify(Microservice oldService, Microservice newService) {
        for (IObserver observer: waitingItems) {
            if(observer.canNotify(oldService)) {
                observer.update(newService);
                // remove observer from waiting list after deleted it
                dettache(observer);
            }
        }
    }

    @Override
    public boolean isInList(AwaitedItem awaitedItem) {

        boolean isInList = false;

        for (IObserver observer: waitingItems) {

            isInList = observer.getAwaitedItem().getBrokenDown().getId() == awaitedItem.getBrokenDown().getId()
                    && observer.getAwaitedItem().getAwaited().getId() == awaitedItem.getAwaited().getId();

            if(isInList)
                break;
        }

        return isInList;
    }

}
