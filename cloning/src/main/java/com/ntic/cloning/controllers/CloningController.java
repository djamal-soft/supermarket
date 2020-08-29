package com.ntic.cloning.controllers;

import com.ntic.cloning.dto.NewServiceDto;
import com.ntic.cloning.models.Microservice;
import com.ntic.cloning.requestHandler.RequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
public class CloningController {

    public static final String projectDir = System.getProperty("user.dir") ;


    @PostMapping(value = "new-instance")
    public Microservice newInstance(@RequestBody Microservice microservice) {
        System.out.println("start new instance");
        System.out.println(microservice);
//        Microservice microserviceInstance = getInstace(microservice);
//
//        if (microserviceInstance != null) { return microserviceInstance; }

//        RunCommandThread thread = null;

        switch (microservice.getName()) {
            case "products":
                runCommand("products");
                break;
            case "categories":
                runCommand("categories");
                break;
            case "clients":
                runCommand("clients");
                break;
            case "employees":
                runCommand("employees");
                break;
            case "orders":
                runCommand("orders");
                break;
        }

        // notify control service for remove microservice from awaited list
//        notifyControlService(microservice);

        return null;

    }

//    private void notifyControlService(Microservice brokenDown) {
//        RequestHandler handler = new RequestHandler();
//        HttpEntity<Microservice> request = new HttpEntity<>(brokenDown);
//        handler.setServiceKey("duplicated")
//                .setServiceVersion(-1)
//                .setRequest(request)
//                .setMethod(RequestHandler.POST)
//                .setResponseType(Object.class)
//                .handle();
//    }


    private void runCommand(String folder) {

        String command = "cd " + projectDir + "/" + folder + "; mvn spring-boot:run";
        System.out.println(command);
        ProcessBuilder processBuilder = new ProcessBuilder();

        processBuilder.command("bash", "-c", command);

        try {
            Process process = processBuilder.start();
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));


        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

}
