package com.supermarket.employeeUI.proxies;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.supermarket.employeeUI.models.Microservice;
import com.supermarket.employeeUI.models.Product;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductProxy {

    public List<Product> allProducts() {
        RequestHandler handler = new RequestHandler();
        ObjectMapper mapper = new ObjectMapper();
        handler.setServiceKey("product-management")
                .setServiceVersion(-1)
                .setResponseType(List.class);

        // response
        List<Product> products = (List<Product>) handler.handle();

        return products;
    }

    public Product getProduct(int id) {
        RequestHandler handler = new RequestHandler();
        handler.setServiceKey("product-management")
                .setServiceVersion(-1)
                .setAdditionnelParamsToUrl(id + "")
                .setResponseType(Product.class);

        return (Product) handler.handle();
    }

    public void addProduct(Product product) {
        RequestHandler handler = new RequestHandler();
        HttpEntity<Product> request = new HttpEntity<>(product);
        handler.setServiceKey("product-management")
                .setServiceVersion(-1)
                .setRequest(request)
                .setMethod(RequestHandler.POST)
                .setResponseType(Product.class)
                .handle();
    }

    public void deleteProduct(int id) {
        RequestHandler handler = new RequestHandler();

        handler.setServiceKey("product-management")
                .setServiceVersion(-1)
                .setAdditionnelParamsToUrl(id + "")
                .setMethod(RequestHandler.DELETE)
                .handle();
    }

    public String uploadImage(MultipartFile multipartFile) throws IOException {
        RequestHandler handler = new RequestHandler();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        LinkedMultiValueMap<String, Object> multipartReqMap = new LinkedMultiValueMap<>();
        multipartReqMap.add("image", multipartFile.getResource());

        HttpEntity<LinkedMultiValueMap<String, Object>> reqEntity = new HttpEntity<>(multipartReqMap, headers);

        handler.setServiceKey("upload-product-image")
                .setServiceVersion(-1)
                .setMethod(RequestHandler.POST)
                .setRequest(reqEntity)
                .setResponseType(String.class);


        return (String) handler.handle();
    }
}
