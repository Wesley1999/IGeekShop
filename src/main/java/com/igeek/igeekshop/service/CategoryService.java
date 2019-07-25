package com.igeek.igeekshop.service;

import com.igeek.igeekshop.util.ServerResponse;
import com.igeek.igeekshop.mapper.CategoryMapper;
import com.igeek.igeekshop.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 王少刚
 * @Time 2019/7/25 14:06
 */

@Service
public class CategoryService {

	@Autowired
	CategoryMapper categoryMapper;

	public ServerResponse<List> getCategories() {
		List<Category> categories = categoryMapper.selectByExample(null);
		for (Category category : categories) {
			category.setDescription(null);
		}
		return ServerResponse.createSuccessResponse(categories);
	}

}
