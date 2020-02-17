package com.igeek.igeekshop.controller;

import com.igeek.igeekshop.util.ServerResponse;
import com.igeek.igeekshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author 王少刚
 * @Time 2019/7/25 8:49
 */

@Controller
@ResponseBody
@RequestMapping("api/category")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@RequestMapping("get_categories.action")
	public ServerResponse<List> getCategories() {
		return categoryService.getCategories();
	}

	@RequestMapping("get_category_name_by_id.action")
	public ServerResponse<String> getCategoryNameById(@RequestParam int categoryId) {
		return categoryService.getCategoryNameById(categoryId);
	}

}
