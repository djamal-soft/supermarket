package com.ntic.cloning.controllers.CommandStrategyPattern.strategyImpl;

import com.ntic.cloning.controllers.CommandStrategyPattern.CommandStrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WindowsCommandImpl implements CommandStrategy {
    @Override
    public void runCommand(String command) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("cmd.exe", "/c", command);

        try {
            Process process = processBuilder.start();
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
