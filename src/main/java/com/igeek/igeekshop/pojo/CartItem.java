package com.igeek.igeekshop.pojo;

import lombok.Data;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class CartItem {
    private Integer cartItemId;

    private String userId;

    private Integer productId;

    private Integer count;

}