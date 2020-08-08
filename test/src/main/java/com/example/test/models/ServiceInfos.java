package com.example.test.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.LocalServerPort;

public class ServiceInfos {

    @LocalServerPort
    private String serverPort;

    public String getServerPort() {
        return serverPort;
    }
}
