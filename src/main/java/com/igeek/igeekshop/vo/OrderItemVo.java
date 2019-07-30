package com.igeek.igeekshop.vo;

import lombok.Data;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @Author 王少刚
 * @Time 2019/7/30 15:16
 */

@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class OrderItemVo {
	private Integer orderItemId;

	private String orderId;

	private Integer productId;

	private Double shopPrice;

	private Integer count;

	private String imgUrl;

	private String productName;
}
