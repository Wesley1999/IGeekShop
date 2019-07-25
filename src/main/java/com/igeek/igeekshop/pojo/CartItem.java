package com.igeek.igeekshop.pojo;

import lombok.Data;

@Data
public class CartItem {
    private Integer cartItemId;

    private String userId;

    private Integer productId;

    private Integer count;

}