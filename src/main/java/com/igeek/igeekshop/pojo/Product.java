package com.igeek.igeekshop.pojo;

import lombok.Data;

@Data
public class Product {
    private Integer productId;

    private String name;

    private Double marketPrice;

    private Double shopPrice;

    private String imgUrl;

    private String description;

    private Boolean isNew;

    private Boolean isHot;

    private Integer categoryId;

}