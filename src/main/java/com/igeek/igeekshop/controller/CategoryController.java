package com.igeek.igeekshop.controller;

import com.igeek.igeekshop.common.ServerResponse;
import com.igeek.igeekshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author 王少刚
 * @Time 2019/7/25 8:49
 */

@Controller
@ResponseBody
@RequestMapping("category")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	public ServerResponse<List> getCategories() {
		return categoryService.getCategories();
	}

}