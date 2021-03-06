package com.igeek.igeekshop.vo;

import lombok.Data;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @Author 王少刚
 * @Time 2019/7/25 15:07
 */

@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class CartVo {

	private int productId;

	private int count;

}
