package com.example.test.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class TestController {
    public static final String projectDir = System.getProperty("user.dir") ;

    @GetMapping(value = "new/{project}")
    @ResponseBody
    public String newInstance(@PathVariable("project") String project) {

//        runCommand(project);

        return "ok";
    }

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

//    @GetMapping(value = "test/{value}")
//    public void testRequest(HttpServletRequest request, @PathVariable String value) {
//
//        ProcessBuilder processBuilder = new ProcessBuilder();
//
//// -- Linux --
//
//// Run a shell command
//        processBuilder.command("bash", "-c", "mkdir ~/bbbbbbb");
//
//// Run a shell script
////processBuilder.command("path/to/hello.sh");
//
//// -- Windows --
//
//// Run a command
////processBuilder.command("cmd.exe", "/c", "dir C:\\Users\\mkyong");
//
//// Run a bat file
////processBuilder.command("C:\\Users\\mkyong\\hello.bat");
//
//        try {
//
//            Process process = processBuilder.start();
//
//            StringBuilder output = new StringBuilder();
//
//            BufferedReader reader = new BufferedReader(
//                    new InputStreamReader(process.getInputStream()));
//
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
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    private void runCommand(String folder) {
//
//        String command = "cd " + projectDir + "/" + folder + "; mvn spring-boot:run";
//        System.out.println(command);
//        ProcessBuilder processBuilder = new ProcessBuilder();
//
//        processBuilder.command("bash", "-c", command);
//
//// Run a shell script
////processBuilder.command("path/to/hello.sh");
//
//// -- Windows --
//
//// Run a command
////processBuilder.command("cmd.exe", "/c", "dir C:\\Users\\mkyong");
//
//// Run a bat file
////processBuilder.command("C:\\Users\\mkyong\\hello.bat");
//
//        try {
//
//            Process process = processBuilder.start();
//
//            StringBuilder output = new StringBuilder();
//
//            BufferedReader reader = new BufferedReader(
//                    new InputStreamReader(process.getInputStream()));
//
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
//
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
//    }

}


