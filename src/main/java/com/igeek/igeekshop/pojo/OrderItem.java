package com.igeek.igeekshop.pojo;

import lombok.Data;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class OrderItem {
    private Integer orderItemId;

    private String orderId;

    private Integer productId;

    private Integer count;

}