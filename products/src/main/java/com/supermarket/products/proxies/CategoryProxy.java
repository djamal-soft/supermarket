package com.supermarket.products.proxies;



import com.supermarket.products.models.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryProxy {


//    public ArrayList<Category> allCategories() {
//
//        RequestHandler handler = new RequestHandler();
//
//        handler.setServiceKey("category-management")
//                .setServiceVersion(-1)
//                .setResponseType(ArrayList.class);
//
//        return (ArrayList<Category>) handler.handle();
//    }

    /**
     *
     * @param ids example: "1,5,8"
     * @return
     */
    public List<Object> categoriesByIds(String ids) {

        RequestHandler handler = new RequestHandler();

        handler.setServiceKey("categories-by-ids")
                .setServiceVersion(-1)
                .setAdditionnelParamsToUrl(ids)
                .setResponseType(ArrayList.class);

        return (List<Object>) handler.handle();
    }
}
