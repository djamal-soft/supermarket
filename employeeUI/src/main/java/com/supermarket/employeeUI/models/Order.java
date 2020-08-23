package com.supermarket.employeeUI.models;

import java.util.Date;
import java.util.List;


public class Order {

    private int id;
    private int clientId;
    private int deliveryManId;
    private String address;
    private Client client;
    private Date orderDate;
    private String status;
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

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", deliveryManId=" + deliveryManId +
                ", address='" + address + '\'' +
                ", client=" + client +
                ", orderDate=" + orderDate +
                ", status=" + status +
                ", orderDetails=" + orderDetails +
                '}';
    }
}
