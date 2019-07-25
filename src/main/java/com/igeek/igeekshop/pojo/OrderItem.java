package com.igeek.igeekshop.pojo;

import lombok.Data;

@Data
public class OrderItem {
    private Integer orderItemId;

    private String orderId;

    private Integer productId;

    private Integer count;

    public Integer getOrderItemId() {
        return orderItemId;
    }

}