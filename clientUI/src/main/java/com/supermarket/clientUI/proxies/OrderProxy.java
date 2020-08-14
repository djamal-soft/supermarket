package com.supermarket.clientUI.proxies;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.supermarket.clientUI.auth.ApplicationUser;
import com.supermarket.clientUI.models.Order;
import org.springframework.http.HttpEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderProxy {


    /**
     * Send order to orders service
     * @param order
     * @return Order
     */
    public Order sendOrder(Order order) {

        HttpEntity<Order> request = new HttpEntity<>(order);
        RequestHandler handler = new RequestHandler();
        handler.setServiceKey("order-management")
                .setServiceVersion(-1)
                .setRequest(request) // for post request
                .setMethod(RequestHandler.POST)
                .setResponseType(Order.class);

        return (Order) handler.handle();
    }

    public boolean deleteOrder(int id) {
        HttpEntity<Integer> request = new HttpEntity<>(id);
        RequestHandler handler = new RequestHandler();
        handler.setServiceKey("order-management")
                .setServiceVersion(-1)
                .setAdditionnelParamsToUrl(id+"") // for post request
                .setMethod(RequestHandler.DELETE)
                .setResponseType(Order.class);

        try {
            handler.handle();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return false;
        }
    }

    /**
     * Get logged in client orders
     * @return List<Order>
     */
    public List<Order> clientOrders() {
        RequestHandler handler = new RequestHandler();
        ObjectMapper mapper = new ObjectMapper();

        handler.setServiceKey("client-orders")
                .setServiceVersion(-1)
                .setMethod(RequestHandler.GET)
                .setAdditionnelParamsToUrl(getClientId()+"")
                .setResponseType(List.class);

        // response
        List<Order> orders = (List<Order>) handler.handle();

        //convert response for orders objects
        ArrayList<Order> convertedOrders = new ArrayList<>();

        for(int i = 0; i < orders.size(); i++) {
            convertedOrders.add(mapper.convertValue(orders.get(i), Order.class));
        }


        return convertedOrders;
    }

    /**
     * Get client id from authentication
     * @return int
     */
    private int getClientId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ApplicationUser client = (ApplicationUser)authentication.getPrincipal();

        return client.getId();
    }
}
