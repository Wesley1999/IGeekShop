package com.igeek.igeekshop.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.igeek.igeekshop.consts.ResponseCodeConst;
import com.igeek.igeekshop.mapper.CategoryMapper;
import com.igeek.igeekshop.mapper.ProductMapper;
import com.igeek.igeekshop.pojo.Category;
import com.igeek.igeekshop.pojo.CategoryExample;
import com.igeek.igeekshop.pojo.Product;
import com.igeek.igeekshop.pojo.ProductExample;
import com.igeek.igeekshop.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 王少刚
 * @Time 2019/7/25 17:07
 */

@Service
public class AdminService {

	@Autowired
	CategoryMapper categoryMapper;

	@Autowired
	ProductMapper productMapper;

	public ServerResponse<List> getCategories() {
		List<Category> categories = categoryMapper.selectByExample(null);
		for (Category category : categories) {
			category.setDescription(null);
		}
		return ServerResponse.createSuccessResponse(categories);
	}

	public ServerResponse<String> addCategory(String name, String description) {
		// 校验分类名称是否已存在
		CategoryExample categoryExample = new CategoryExample();
		categoryExample.createCriteria().andNameEqualTo(name);
		if (!categoryMapper.selectByExample(categoryExample).isEmpty()) {
			return ServerResponse.createErrorResponse(ResponseCodeConst.CATEGORY_NAME_EXISTS);
		}

		Category category = new Category();
		category.setName(name);
		category.setDescription(description);
		categoryMapper.insert(category);

		return ServerResponse.createSuccessResponse();

	}

	public ServerResponse<Category> getCategory(int categoryId) {
		Category category = categoryMapper.selectByPrimaryKey(categoryId);
		// 校验categoryId
		if (category == null) {
			return ServerResponse.createErrorResponse(ResponseCodeConst.ERROR_CATEGORY_ID);
		}
		return ServerResponse.createSuccessResponse(category);
	}

	public ServerResponse<String> updateCategory(int categoryId, String name, String description) {
		// 校验categoryId
		if (categoryMapper.selectByPrimaryKey(categoryId) == null) {
			return ServerResponse.createErrorResponse(ResponseCodeConst.ERROR_CATEGORY_ID);
		}
		// 校验name是否已存在（查找时忽略当前categoryId）
		CategoryExample categoryExample = new CategoryExample();
		categoryExample.createCriteria().andNameEqualTo(name).andCategoryIdNotEqualTo(categoryId);
		if (!categoryMapper.selectByExample(categoryExample).isEmpty()) {
			return ServerResponse.createErrorResponse(ResponseCodeConst.CATEGORY_NAME_EXISTS);
		}

		Category category = new Category();
		category.setCategoryId(categoryId);
		category.setName(name);
		category.setDescription(description);
		categoryMapper.updateByPrimaryKeySelective(category);

		return ServerResponse.createSuccessResponse();
	}

	public ServerResponse<String> deleteCategory(int categoryId) {
		// 校验categoryId
		if (categoryMapper.selectByPrimaryKey(categoryId) == null) {
			return ServerResponse.createErrorResponse(ResponseCodeConst.ERROR_CATEGORY_ID);
		}

		// 校验category是否有product使用
		ProductExample productExample = new ProductExample();
		productExample.createCriteria().andCategoryIdEqualTo(categoryId);
		if (!productMapper.selectByExample(productExample).isEmpty()) {
			return ServerResponse.createErrorResponse(ResponseCodeConst.CATEGORY_IS_USED_BY_PRODUCT);
		}

		// 删除
		categoryMapper.deleteByPrimaryKey(categoryId);

		return ServerResponse.createSuccessResponse();
	}


	public ServerResponse<PageInfo> getProducts(int pageNum, int pageSize, int navigatePages) {
		PageHelper.startPage(pageNum, pageSize);
		List<Product> products = productMapper.selectByExample(null);
		PageInfo pageResult = new PageInfo(products, navigatePages);
		pageResult.setList(products);
		return ServerResponse.createSuccessResponse(pageResult);
	}

}
