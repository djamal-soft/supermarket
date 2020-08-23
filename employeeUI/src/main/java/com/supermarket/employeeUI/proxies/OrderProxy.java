package com.supermarket.employeeUI.proxies;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.supermarket.clientUI.auth.ApplicationUser;
import com.supermarket.employeeUI.auth.ApplicationUser;
import com.supermarket.employeeUI.models.Order;
import org.springframework.http.HttpEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
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
    public List<Order> deliveryManOrders() {
        RequestHandler handler = new RequestHandler();
        ObjectMapper mapper = new ObjectMapper();

        handler.setServiceKey("delivery-man-orders")
                .setServiceVersion(-1)
                .setMethod(RequestHandler.GET)
                .setAdditionnelParamsToUrl(getEmployeeId() + "")
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
     * change status of the order to next status
     * @param id
     */
    public void orderNextStatus(int id) {
        RequestHandler handler = new RequestHandler();
        handler.setServiceKey("next-status")
                .setServiceVersion(-1)
                .setAdditionnelParamsToUrl(id+"")
                .setMethod(RequestHandler.GET)
                .setResponseType(Order.class)
                .handle();
    }

    public void refuseOrder(int id) {
        RequestHandler handler = new RequestHandler();
        handler.setServiceKey("refuse-order")
                .setServiceVersion(-1)
                .setAdditionnelParamsToUrl(id+"")
                .setMethod(RequestHandler.GET)
                .setResponseType(Order.class)
                .handle();
    }

    /**
     * Get logged id employee id
     * @return int
     */
    private int getEmployeeId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ApplicationUser client = (ApplicationUser)authentication.getPrincipal();

        return client.getId();
    }
}
