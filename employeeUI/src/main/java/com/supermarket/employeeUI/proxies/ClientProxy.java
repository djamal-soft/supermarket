package com.supermarket.employeeUI.proxies;

import com.supermarket.employeeUI.models.Client;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientProxy {

    public List<Client> allClients() {
        RequestHandler handler = new RequestHandler();
        handler.setServiceKey("client-management")
                .setServiceVersion(-1)
                .setMethod(RequestHandler.GET)
                .setResponseType(List.class);

        return (List<Client>) handler.handle();
    }

    public void deleteClient(int id) {
        RequestHandler handler = new RequestHandler();
        handler.setServiceKey("client-management")
                .setServiceVersion(-1)
                .setAdditionnelParamsToUrl(id+"")
                .setMethod(RequestHandler.DELETE)
                .handle();
    }
}
