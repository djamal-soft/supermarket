package com.supermarket.clientUI.proxies;

import com.supermarket.clientUI.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

@Service
public class ClientProxy {



    public void registerClient(Client client) {
        HttpEntity<Client> request = new HttpEntity<>(client);
        RequestHandler handler = new RequestHandler();
        handler.setServiceKey("client-management")
                .setServiceVersion(-1)
                .setRequest(request)
                .setMethod(RequestHandler.POST)
                .setResponseType(Client.class)
                .handle();
    }
}
