package com.igeek.igeekshop.vo;

import lombok.Data;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @Author 王少刚
 * @Time 2019/7/30 13:59
 */

@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ProductVo {
	private Integer productId;

	private String name;

	private Double marketPrice;

	private Double shopPrice;

	private String imgUrl;

	private String description;

	private Boolean isNew;

	private Boolean isHot;

	private Integer categoryId;

	private String categoryName;
}
