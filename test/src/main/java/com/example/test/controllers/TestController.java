package com.example.test.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Controller
public class TestController {

    @GetMapping(value = "/")
    public String index(Model model) {

        return "index";
    }

    public void getServiceInfos() {
        try {
            String ip = InetAddress.getLocalHost().getHostAddress();
            System.out.println(ip);
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        }
    }

    @GetMapping(value = "test/{value}")
    public void testRequest(HttpServletRequest request, @PathVariable String value) {

        ProcessBuilder processBuilder = new ProcessBuilder();

// -- Linux --

// Run a shell command
        processBuilder.command("bash", "-c", "mkdir ~/bb");

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

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println("Success!");
                System.out.println(output);
                System.exit(0);
            } else {
                //abnormal...
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}


