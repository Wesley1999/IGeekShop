package com.igeek.igeekshop.vo;

import com.igeek.igeekshop.pojo.Orders;
import com.igeek.igeekshop.pojo.OrderItem;
import lombok.Data;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.List;

/**
 * @Author 王少刚
 * @Time 2019/7/25 16:42
 */

@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class OrderVo {

	private Orders orders;

	private List<OrderItem> orderItems;

}
