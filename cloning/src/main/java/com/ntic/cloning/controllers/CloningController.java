package com.ntic.cloning.controllers;

import com.ntic.cloning.dao.MicroserviceDao;
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

    @Autowired
    private MicroserviceDao microserviceDao;

    @PostMapping(value = "new-instance")
    public Microservice newInstance(@RequestBody Microservice microservice) {
        Microservice microserviceInstance = getInstace(microservice);

        if (microserviceInstance != null) { return microserviceInstance; }

//        RunCommandThread thread = null;

        switch (microservice.getName()) {
            case "products":
                runCommand("products");
//                thread = new RunCommandThread(getCommand("products"));
                break;
            case "categories":
                runCommand("categories");
//                thread = new RunCommandThread(getCommand("categories"));
                break;
            case "clients":
                runCommand("clients");
//                thread = new RunCommandThread(getCommand("clients"));
                break;
            case "employees":
                runCommand("employees");
//                thread = new RunCommandThread(getCommand("employees"));
                break;
            case "orders":
                runCommand("orders");
//                thread = new RunCommandThread(getCommand("orders"));
                break;
        }

        // notify control service for remove microservice from awaited list
        notifyControlService(microservice);

        return null;

    }

    private void notifyControlService(Microservice brokenDown) {
        RequestHandler handler = new RequestHandler();
        HttpEntity<Microservice> request = new HttpEntity<>(brokenDown);
        handler.setServiceKey("duplicated")
                .setServiceVersion(-1)
                .setRequest(request)
                .setMethod(RequestHandler.POST)
                .setResponseType(Object.class)
                .handle();
    }

    private Microservice getInstace(Microservice microservice) {

        Microservice microserviceInstance =
                microserviceDao.findByMkeysAndAddressAndVersion(
                        microservice.getMkeys(),
                        microservice.getAddress(),
                        microservice.getVersion()
                );

        return microserviceInstance;
    }

    private void runCommand(String folder) {

        String command = "cd " + projectDir + "/" + folder + "; mvn spring-boot:run";
        System.out.println(command);
        ProcessBuilder processBuilder = new ProcessBuilder();

        processBuilder.command("bash", "-c", command);

// Run a shell script
//processBuilder.command("path/to/hello.sh");

// -- Windows --

// Run a command
//processBuilder.command("cmd.exe", "/c", "dir C:\\Users\\mkyong");

// Run a bat file
//processBuilder.command("C:\\Users\\mkyong\\hello.bat");

        try {
            Process process = processBuilder.start();
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                output.append(line + "\n");
//            }
//
//            int exitVal = process.waitFor();
//            if (exitVal == 0) {
//                System.out.println("Success!");
//                System.out.println(output);
//                System.exit(0);
//            } else {
//                //abnormal...
//            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
//        catch (InterruptedException e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
    }

}
