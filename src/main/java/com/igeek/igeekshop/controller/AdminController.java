package com.igeek.igeekshop.controller;

import com.github.pagehelper.PageInfo;
import com.igeek.igeekshop.consts.DefaultValueConst;
import com.igeek.igeekshop.pojo.Category;
import com.igeek.igeekshop.pojo.Product;
import com.igeek.igeekshop.service.AdminService;
import com.igeek.igeekshop.util.ServerResponse;
import com.qiniu.common.QiniuException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @Author 王少刚
 * @Time 2019/7/25 17:07
 */

@Controller
@ResponseBody
@RequestMapping("api/admin")
public class AdminController {

	@Autowired
	AdminService adminService;

	// 获取所有分类
	@RequestMapping("get_categories")
	public ServerResponse<List> getCategories() {
		return adminService.getCategories();
	}

	// 添加分类
	@RequestMapping("add_category")
	public ServerResponse<String> addCategory(@RequestParam String name, @RequestParam String description) {
		return adminService.addCategory(name, description);
	}

	// 获取根据id分类详情
	@RequestMapping("get_category")
	public ServerResponse<Category> getCategory(@RequestParam int categoryId) {
		return adminService.getCategory(categoryId);
	}

	// 编辑分类
	@RequestMapping("update_category")
	public ServerResponse<String> updateCategory(@RequestParam int categoryId, @RequestParam String name, @RequestParam String description) {
		return adminService.updateCategory(categoryId, name, description);
	}

	// 删除分类
	@RequestMapping("delete_category")
	public ServerResponse<String> deleteCategory(@RequestParam int categoryId) {
		return adminService.deleteCategory(categoryId);
	}

	//--------------------------------------------------------------------------------------

	// 获取所有商品
	@RequestMapping("get_products")
	public ServerResponse<PageInfo> getProducts(@RequestParam(defaultValue = DefaultValueConst.DEFAULT_PAGE_NUM) int pageNum,
	                                            @RequestParam(defaultValue = DefaultValueConst.DEFAULT_PAGE_SIZE) int pageSize,
	                                            @RequestParam(defaultValue = DefaultValueConst.DEFAULT_NAVIGATE_PAGES) int navigatePages) {
		return adminService.getProducts(pageNum, pageSize, navigatePages);
	}

	// 添加商品（含文件上传）
	// 仅post
	@RequestMapping(value = "add_product", method = RequestMethod.POST)
	public ServerResponse<String> addProduct(@RequestParam String name, @RequestParam double marketPrice,
	                                         @RequestParam double shopPrice, @RequestParam String description,
	                                         @RequestParam boolean isNew, @RequestParam boolean isHot,
	                                         @RequestParam int categoryId, @RequestParam MultipartFile imageFile) throws IOException {
		return adminService.addProduct(name, marketPrice, shopPrice, description, isNew, isHot, categoryId, imageFile);
	}

	// 根据id获取商品详情
	@RequestMapping("get_product")
	public ServerResponse<Product> getProduct(int productId) {
		return adminService.getProduct(productId);
	}

	// 编辑商品
	@RequestMapping("update_product")
	public ServerResponse<String> updateProduct(@RequestParam int productId, @RequestParam String name, @RequestParam double marketPrice,
	                                            @RequestParam double shopPrice, @RequestParam String description,
	                                            @RequestParam boolean isNew, @RequestParam boolean isHot,
	                                            @RequestParam int categoryId) {
		return adminService.updateProduct(productId, name, marketPrice, shopPrice, description, isNew, isHot, categoryId);
	}

	@RequestMapping("update_product_image")
	public ServerResponse<String> updateProductImage(@RequestParam int productId, @RequestParam MultipartFile imageFile) throws QiniuException {
		return adminService.updateProductImage(productId, imageFile);
	}

	// 删除商品
	@RequestMapping("delete_product")
	public ServerResponse<String> deleteProduct(@RequestParam int productId) {
		return adminService.deleteProduct(productId);
	}

}
