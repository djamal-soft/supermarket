package com.ntic.control.dto;

import com.ntic.control.models.Microservice;

public class AwaitedItemDto {
    private Microservice awaited;
    private Microservice brokenDown;

    public AwaitedItemDto() {
    }

    public AwaitedItemDto(Microservice awaited, Microservice brokenDown) {
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
