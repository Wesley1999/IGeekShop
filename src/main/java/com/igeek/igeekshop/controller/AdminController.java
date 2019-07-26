package com.igeek.igeekshop.controller;

import com.igeek.igeekshop.pojo.Category;
import com.igeek.igeekshop.service.AdminService;
import com.igeek.igeekshop.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

	//-----------------------------------------------------------------------------------

	// 获取所有商品

	// 添加商品（涉及文件上传）

	// 根据id获取商品详情

	// 编辑商品（涉及文件上传）

	// 删除商品

}
