package com.igeek.igeekshop.controller;

import com.github.pagehelper.PageInfo;
import com.igeek.igeekshop.common.DefaultValueConst;
import com.igeek.igeekshop.common.ServerResponse;
import com.igeek.igeekshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author 王少刚
 * @Time 2019/7/25 8:49
 */

@Service
public class ProductController {

	@Autowired
	ProductService productService;

	public ServerResponse<PageInfo> getProductsByCategoryId(@RequestParam(defaultValue = DefaultValueConst.DEFAULT_PAGE_NUM) int pageNum,
	                                                        @RequestParam(defaultValue = DefaultValueConst.DEFAULT_PAGE_SIZE) int pageSize,
	                                                        @RequestParam(defaultValue = DefaultValueConst.DEFAULT_NAVIGATE_PAGES) int navigatePages,
	                                                        @RequestParam int categoryId) {
		return productService.getProductsByCategoryId(pageNum, pageSize, navigatePages, categoryId);
	}

}
