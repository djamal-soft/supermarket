package com.supermarket.orders.proxies;


import com.supermarket.orders.requestHandler.RequestHandler;
import org.springframework.stereotype.Component;

@Component
public class ProductProxy {

    public Object getProduct(int id) {

        RequestHandler handler = new RequestHandler();

        handler.setServiceKey("product-management")
                .setServiceVersion(-1)
                .setAdditionnelParamsToUrl(id + "")
                .setResponseType(Object.class);

        return handler.handle();
    }
}
