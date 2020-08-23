package com.supermarket.orders.proxies;

import com.supermarket.orders.requestHandler.RequestHandler;
import org.springframework.stereotype.Service;

@Service
public class ClientProxy {

    public Object getClientById(int id) {
        RequestHandler handler = new RequestHandler();

        handler.setServiceKey("client-management")
                .setServiceVersion(-1)
                .setAdditionnelParamsToUrl(id+"")
                .setResponseType(Object.class)
                .setMethod(RequestHandler.GET);

        return handler.handle();
    }
}
