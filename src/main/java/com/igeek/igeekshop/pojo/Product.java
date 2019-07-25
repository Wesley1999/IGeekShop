package com.igeek.igeekshop.pojo;

import lombok.Data;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
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