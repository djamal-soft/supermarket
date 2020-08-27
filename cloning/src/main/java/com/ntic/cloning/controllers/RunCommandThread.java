package com.ntic.cloning.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RunCommandThread extends Thread {

    private String command;

    public RunCommandThread(String command) {
        this.command = command;
    }

    @Override
    public void run() {
        runCommand();
    }

    private void runCommand() {

//        String command = "cd " + projectDir + "/" + folder + "; mvn spring-boot:run";
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
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

}
