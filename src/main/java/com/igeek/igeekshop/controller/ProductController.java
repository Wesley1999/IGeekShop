package com.igeek.igeekshop.controller;

import com.github.pagehelper.PageInfo;
import com.igeek.igeekshop.common.DefaultValueConst;
import com.igeek.igeekshop.common.ServerResponse;
import com.igeek.igeekshop.pojo.Product;
import com.igeek.igeekshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author 王少刚
 * @Time 2019/7/25 8:49
 */

@Controller
@ResponseBody
@RequestMapping("api/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@RequestMapping("get_products_by_category_id")
	public ServerResponse<PageInfo> getProductsByCategoryId(@RequestParam(defaultValue = DefaultValueConst.DEFAULT_PAGE_NUM) int pageNum,
	                                                        @RequestParam(defaultValue = DefaultValueConst.DEFAULT_PAGE_SIZE) int pageSize,
	                                                        @RequestParam(defaultValue = DefaultValueConst.DEFAULT_NAVIGATE_PAGES) int navigatePages,
	                                                        @RequestParam int categoryId) {
		return productService.getProductsByCategoryId(pageNum, pageSize, navigatePages, categoryId);
	}

	@RequestMapping("get_hot_products")
	public ServerResponse<PageInfo> getHotProducts(@RequestParam(defaultValue = DefaultValueConst.DEFAULT_PAGE_NUM) int pageNum,
	                                               @RequestParam(defaultValue = DefaultValueConst.DEFAULT_PAGE_SIZE) int pageSize,
	                                               @RequestParam(defaultValue = DefaultValueConst.DEFAULT_NAVIGATE_PAGES) int navigatePages) {
		return productService.getHotProducts(pageNum, pageSize, navigatePages);
	}

	@RequestMapping("get_new_products")
	public ServerResponse<PageInfo> getNewProducts(@RequestParam(defaultValue = DefaultValueConst.DEFAULT_PAGE_NUM) int pageNum,
	                                               @RequestParam(defaultValue = DefaultValueConst.DEFAULT_PAGE_SIZE) int pageSize,
	                                               @RequestParam(defaultValue = DefaultValueConst.DEFAULT_NAVIGATE_PAGES) int navigatePages) {
		return productService.getNewProducts(pageNum, pageSize, navigatePages);
	}

	@RequestMapping("search_products")
	public ServerResponse<PageInfo> searchProducts(@RequestParam(defaultValue = DefaultValueConst.DEFAULT_PAGE_NUM) int pageNum,
	                                               @RequestParam(defaultValue = DefaultValueConst.DEFAULT_PAGE_SIZE) int pageSize,
	                                               @RequestParam(defaultValue = DefaultValueConst.DEFAULT_NAVIGATE_PAGES) int navigatePages,
	                                               @RequestParam(defaultValue = "") String keyword) {
		return productService.searchProducts(pageNum, pageSize, navigatePages, keyword);
	}

	@RequestMapping("get_product")
	public ServerResponse<Product> getProduct(int productId) {
		return productService.getProduct(productId);
	}

}
