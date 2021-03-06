package com.igeek.igeekshop.controller;

import com.github.pagehelper.PageInfo;
import com.igeek.igeekshop.consts.DefaultValueConst;
import com.igeek.igeekshop.service.ProductService;
import com.igeek.igeekshop.util.ServerResponse;
import com.igeek.igeekshop.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

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

	@RequestMapping("get_products_by_category_id.action")
	public ServerResponse<PageInfo> getProductsByCategoryId(@RequestParam(defaultValue = DefaultValueConst.DEFAULT_PAGE_NUM) int pageNum,
	                                                        @RequestParam(defaultValue = DefaultValueConst.DEFAULT_PAGE_SIZE) int pageSize,
	                                                        @RequestParam(defaultValue = DefaultValueConst.DEFAULT_NAVIGATE_PAGES) int navigatePages,
	                                                        @RequestParam int categoryId) {
		return productService.getProductsByCategoryId(pageNum, pageSize, navigatePages, categoryId);
	}

	@RequestMapping("get_hot_products.action")
	public ServerResponse<PageInfo> getHotProducts(@RequestParam(defaultValue = DefaultValueConst.DEFAULT_PAGE_NUM) int pageNum,
	                                               @RequestParam(defaultValue = DefaultValueConst.DEFAULT_PAGE_SIZE) int pageSize,
	                                               @RequestParam(defaultValue = DefaultValueConst.DEFAULT_NAVIGATE_PAGES) int navigatePages) {
		return productService.getHotProducts(pageNum, pageSize, navigatePages);
	}

	@RequestMapping("get_new_products.action")
	public ServerResponse<PageInfo> getNewProducts(@RequestParam(defaultValue = DefaultValueConst.DEFAULT_PAGE_NUM) int pageNum,
	                                               @RequestParam(defaultValue = DefaultValueConst.DEFAULT_PAGE_SIZE) int pageSize,
	                                               @RequestParam(defaultValue = DefaultValueConst.DEFAULT_NAVIGATE_PAGES) int navigatePages) {
		return productService.getNewProducts(pageNum, pageSize, navigatePages);
	}

	@RequestMapping("search_products.action")
	public ServerResponse<PageInfo> searchProducts(@RequestParam(defaultValue = DefaultValueConst.DEFAULT_PAGE_NUM) int pageNum,
	                                               @RequestParam(defaultValue = DefaultValueConst.DEFAULT_PAGE_SIZE) int pageSize,
	                                               @RequestParam(defaultValue = DefaultValueConst.DEFAULT_NAVIGATE_PAGES) int navigatePages,
	                                               @RequestParam(defaultValue = "") String keyword) {
		return productService.searchProducts(pageNum, pageSize, navigatePages, keyword);
	}

	@RequestMapping("get_product.action")
	public ServerResponse<ProductVo> getProduct(int productId) {
		return productService.getProduct(productId);
	}

	// 记录浏览记录
	@RequestMapping("record_browsing_history.action")
	public ServerResponse<String> recordBrowsingHistory(HttpSession session, @RequestParam int productId) {
		return productService.recordBrowsingHistory(session, productId);
	}

	@RequestMapping("get_browsing_history.action")
	public ServerResponse<List> getBrowsingHistory(HttpSession session) {
		return productService.getBrowsingHistory(session);
	}

}
