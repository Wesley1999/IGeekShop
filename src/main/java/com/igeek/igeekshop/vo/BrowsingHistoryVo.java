package com.igeek.igeekshop.vo;

import lombok.Data;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @Author 王少刚
 * @Time 2019/7/30 14:25
 */

@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class BrowsingHistoryVo {

	Integer productId;

	String productName;

	String imgUrl;

}
