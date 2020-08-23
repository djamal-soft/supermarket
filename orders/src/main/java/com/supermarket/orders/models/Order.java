package com.supermarket.orders.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import com.supermarket.orders.enums.OrderStatus;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue
    private int id;
    private int clientId;
    private int deliveryManId;
    private String address;

    @Transient
    private Object client;

    @Temporal(TemporalType.DATE)
    private Date orderDate;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;

    @JsonIgnoreProperties("order")
    @OneToMany(mappedBy = "order")
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

    public Object getClient() {
        return client;
    }

    public void setClient(Object client) {
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

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
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
