package com.ntic.cloning.controllers;

import com.ntic.cloning.controllers.CommandStrategyPattern.CommandStrategy;
import com.ntic.cloning.controllers.CommandStrategyPattern.strategyImpl.LinuxCommandImpl;
import com.ntic.cloning.controllers.CommandStrategyPattern.strategyImpl.WindowsCommandImpl;
import com.ntic.cloning.models.Microservice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class CloningController {

    public static final String projectDir = System.getProperty("user.dir") ;

    private CommandStrategy commandStrategy;


    @PostMapping(value = "new-instance")
    public Microservice newInstance(@RequestBody Microservice microservice) {

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

        return null;

    }


    private void runCommand(String folder) {

        setCommandStrategy();

        String command = "cd " + projectDir + "/" + folder + "; mvn spring-boot:run";

        commandStrategy.runCommand(command);
    }


    public void setCommandStrategy () {
        String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
        if (OS.indexOf("win") >= 0) {
            commandStrategy = new WindowsCommandImpl();
        } else if (OS.indexOf("nux") >= 0) {
            commandStrategy = new LinuxCommandImpl();
        }
    }

}
