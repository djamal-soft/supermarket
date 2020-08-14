package com.supermarket.clientUI.proxies;


import com.supermarket.clientUI.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductProxy {


    public ArrayList<Product> allProducts() {
        RequestHandler handler = new RequestHandler();
        handler.setResponseType(ArrayList.class)
                .setServiceKey("product-management")
                .setServiceVersion(-1);

        return (ArrayList<Product>) handler.handle();
    }

    public ArrayList<Product> productsByCategory(String categoryId) {
        RequestHandler handler = new RequestHandler();
        handler.setResponseType(ArrayList.class)
                .setServiceKey("category-products")
                .setServiceVersion(-1)
                .setAdditionnelParamsToUrl(categoryId);

        return (ArrayList<Product>) handler.handle();
    }

    public Product productById(int id) {
        RequestHandler handler = new RequestHandler();
        handler.setResponseType(Product.class)
                .setServiceKey("product-management")
                .setServiceVersion(-1)
                .setAdditionnelParamsToUrl(id+"");

        return (Product) handler.handle();
    }
}
