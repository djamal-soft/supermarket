package com.ntic.cloning.dto;


import com.ntic.cloning.models.Microservice;

public class NewServiceDto {

    private Microservice oldService;
    private Microservice newService;

    public NewServiceDto() {
    }

    public Microservice getOldService() {
        return oldService;
    }

    public void setOldService(Microservice oldService) {
        this.oldService = oldService;
    }

    public Microservice getNewService() {
        return newService;
    }

    public void setNewService(Microservice newService) {
        this.newService = newService;
    }

    @Override
    public String toString() {
        return "NewServiceDto{" +
                "oldService=" + oldService +
                ", newService=" + newService +
                '}';
    }
}
