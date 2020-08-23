package com.supermarket.employeeUI.proxies;


import com.supermarket.employeeUI.models.Category;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class CategoryProxy {


    public ArrayList<Category> allCategories() {

        RequestHandler handler = new RequestHandler();

        handler.setServiceKey("category-management")
                .setServiceVersion(-1)
                .setResponseType(ArrayList.class);

        return (ArrayList<Category>) handler.handle();
    }

    public Category getCategoryById(int id) {

        RequestHandler handler = new RequestHandler();

        handler.setServiceKey("category-management")
                .setServiceVersion(-1)
                .setAdditionnelParamsToUrl(id + "")
                .setResponseType(Category.class);

        return (Category) handler.handle();
    }

    /**
     *
     * @param ids example: "1,5,8"
     * @return
     */
    public ArrayList<Category> categoriesByIds(String ids) {

        RequestHandler handler = new RequestHandler();

        handler.setServiceKey("categories-by-ids")
                .setServiceVersion(-1)
                .setAdditionnelParamsToUrl(ids)
                .setResponseType(ArrayList.class);

        return (ArrayList<Category>) handler.handle();
    }

    public void addCategory(Category category) {
        RequestHandler handler = new RequestHandler();
        HttpEntity<Category> request = new HttpEntity<>(category);
        handler.setServiceKey("category-management")
                .setServiceVersion(-1)
                .setRequest(request)
                .setMethod(RequestHandler.POST)
                .setResponseType(Category.class)
                .handle();
    }

    public String uploadImage(MultipartFile multipartFile) throws IOException {
        RequestHandler handler = new RequestHandler();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        LinkedMultiValueMap<String, Object> multipartReqMap = new LinkedMultiValueMap<>();
        multipartReqMap.add("image", multipartFile.getResource());

        HttpEntity<LinkedMultiValueMap<String, Object>> reqEntity = new HttpEntity<>(multipartReqMap, headers);

        handler.setServiceKey("upload-category-image")
                .setServiceVersion(-1)
                .setMethod(RequestHandler.POST)
                .setRequest(reqEntity)
                .setResponseType(String.class);


        return (String) handler.handle();
    }

    public void deleteCategory(int id) {
        RequestHandler handler = new RequestHandler();

        handler.setServiceKey("category-management")
                .setServiceVersion(-1)
                .setAdditionnelParamsToUrl(id + "")
                .setMethod(RequestHandler.DELETE)
                .handle();
    }
}
