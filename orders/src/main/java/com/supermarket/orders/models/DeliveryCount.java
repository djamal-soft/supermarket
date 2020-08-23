package com.supermarket.orders.models;

public class DeliveryCount {
    private Integer deliveryManId;
    private Integer count;

    public DeliveryCount(Integer deliveryManId, Integer count) {
        this.deliveryManId = deliveryManId;
        this.count = count;
    }

    public Integer getDeliveryManId() {
        return deliveryManId;
    }

    public void setDeliveryManId(Integer deliveryManId) {
        this.deliveryManId = deliveryManId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "DeliveryCount{" +
                "deliveryManId=" + deliveryManId +
                ", count=" + count +
                '}';
    }
}
