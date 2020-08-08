package com.supermarket.clientUI.proxies;


import com.supermarket.clientUI.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class ProductProxy {


    public ArrayList<Product> allProducts() {
        RequestHandler handler = new RequestHandler();
        handler.setResponseType(ArrayList.class)
                .setServiceKey("product-management")
                .setServiceVersion(-1);

        return (ArrayList<Product>) handler.handleGetRequest();
    }

    public ArrayList<Product> productsByCategory(String categoryId) {

        RequestHandler handler = new RequestHandler();
        handler.setResponseType(ArrayList.class)
                .setServiceKey("category-products")
                .setServiceVersion(-1)
                .setAdditionnelParamsToUrl(categoryId);

        return (ArrayList<Product>) handler.handleGetRequest();
    }

    public Product productById(int id) {

        RequestHandler handler = new RequestHandler();
        handler.setResponseType(Product.class)
                .setServiceKey("product-management")
                .setServiceVersion(-1)
                .setAdditionnelParamsToUrl(id+"");

        return (Product) handler.handleGetRequest();
    }
}
