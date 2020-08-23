package com.supermarket.clientUI.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;
import java.util.List;

public class Order {

    private int id;
    private int clientId;
    private int deliveryManId;
    private Client client;
    private String address;
    private Date orderDate;
    private String status;

    @JsonDeserialize
    private List<OrderDetails> orderDetails;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getDeliveryManId() {
        return deliveryManId;
    }

    public void setDeliveryManId(int deliveryManId) {
        this.deliveryManId = deliveryManId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public int nbProducts() {

        int nb = 0;

        for(OrderDetails details: orderDetails) {
            nb += details.getQuantity();
        }
        return nb;
    }

    public int totalPrice() {
        int total = 0;

        for(OrderDetails details: orderDetails) {
            total += (details.getQuantity() * details.getProduct().getPrice());
        }
        return total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", deliveryManId=" + deliveryManId +
                ", client=" + client +
                ", address='" + address + '\'' +
                ", orderDate=" + orderDate +
                ", status='" + status + '\'' +
                ", orderDetails=" + orderDetails +
                '}';
    }
}
