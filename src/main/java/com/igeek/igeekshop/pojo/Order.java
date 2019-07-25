package com.igeek.igeekshop.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private String orderId;

    private Date orderTime;

    private Double totalPrice;

    private String recipientName;

    private String telephone;

    private String address;

    private Integer status;

    private String userId;

}