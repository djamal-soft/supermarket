package com.ntic.control.models;

public class AwaitedItem {
    private Microservice awaited;
    private Microservice brokenDown;

    public AwaitedItem() {
    }

    public AwaitedItem(Microservice awaited, Microservice brokenDown) {
        this.awaited = awaited;
        this.brokenDown = brokenDown;
    }

    public Microservice getAwaited() {
        return awaited;
    }

    public void setAwaited(Microservice awaited) {
        this.awaited = awaited;
    }

    public Microservice getBrokenDown() {
        return brokenDown;
    }

    public void setBrokenDown(Microservice brokenDown) {
        this.brokenDown = brokenDown;
    }
}
