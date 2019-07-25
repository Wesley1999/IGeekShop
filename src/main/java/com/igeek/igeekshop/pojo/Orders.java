package com.igeek.igeekshop.pojo;

import lombok.Data;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Orders {
    private String orderId;

    private Date orderTime;

    private Double totalPrice;

    private String recipientName;

    private String telephone;

    private String address;

    private Integer status;

    private String userId;

}