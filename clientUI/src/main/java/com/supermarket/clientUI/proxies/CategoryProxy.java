package com.supermarket.clientUI.proxies;

import com.supermarket.clientUI.models.Category;
import com.supermarket.clientUI.models.Microservice;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class CategoryProxy {

    public ArrayList<Category> allCategories() {

        RequestHandler handler = new RequestHandler();

        handler.setServiceKey("category-management")
                .setServiceVersion(-1)
                .setResponseType(ArrayList.class);

        return (ArrayList<Category>) handler.handleGetRequest();
    }
}
