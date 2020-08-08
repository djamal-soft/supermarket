package com.supermarket.orders.models;

import javax.persistence.*;

@Entity
@Table(name = "order_details")
public class OrderDetails {

//    private int orderId;
    @Id
    @GeneratedValue
    private int id;
    private int productId;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Transient
    private Object product;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    //    public int getOrderId() {
//        return orderId;
//    }
//
//    public void setOrderId(int orderId) {
//        this.orderId = orderId;
//    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Object getProduct() {
        return product;
    }

    public void setProduct(Object product) {
        this.product = product;
    }
}
