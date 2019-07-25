package com.igeek.igeekshop.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.igeek.igeekshop.common.ResponseCodeConst;
import com.igeek.igeekshop.common.ServerResponse;
import com.igeek.igeekshop.mapper.ProductMapper;
import com.igeek.igeekshop.pojo.Product;
import com.igeek.igeekshop.pojo.ProductExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 王少刚
 * @Time 2019/7/25 14:28
 */

@Service
public class ProductService {

	@Autowired
	ProductMapper productMapper;

	public ServerResponse<PageInfo> getProductsByCategoryId(int pageNum, int pageSize, int navigatePages, int categoryId) {
		ProductExample productExample = new ProductExample();
		productExample.createCriteria().andCategoryIdEqualTo(categoryId);
		PageHelper.startPage(pageNum, pageSize);
		List<Product> products = productMapper.selectByExample(productExample);
		PageInfo pageResult = new PageInfo(products, navigatePages);
		pageResult.setList(products);
		return ServerResponse.createSuccessResponse(pageResult);
	}

	public ServerResponse<PageInfo> getHotProducts(int pageNum, int pageSize, int navigatePages) {
		ProductExample productExample = new ProductExample();
		productExample.createCriteria().andIsHotEqualTo(true);
		PageHelper.startPage(pageNum, pageSize);
		List<Product> products = productMapper.selectByExample(productExample);
		PageInfo pageResult = new PageInfo(products, navigatePages);
		pageResult.setList(products);
		return ServerResponse.createSuccessResponse(pageResult);
	}

	public ServerResponse<PageInfo> getNewProducts(int pageNum, int pageSize, int navigatePages) {
		ProductExample productExample = new ProductExample();
		productExample.createCriteria().andIsNewEqualTo(true);
		PageHelper.startPage(pageNum, pageSize);
		List<Product> products = productMapper.selectByExample(productExample);
		PageInfo pageResult = new PageInfo(products, navigatePages);
		pageResult.setList(products);
		return ServerResponse.createSuccessResponse(pageResult);
	}

	public ServerResponse<PageInfo> searchProducts(int pageNum, int pageSize, int navigatePages, String keyword) {
		ProductExample productExample = new ProductExample();
		productExample.createCriteria().andNameLike("%" + keyword + "%");
		PageHelper.startPage(pageNum, pageSize);
		List<Product> products = productMapper.selectByExample(productExample);
		PageInfo pageResult = new PageInfo(products, navigatePages);
		pageResult.setList(products);
		return ServerResponse.createSuccessResponse(pageResult);
	}

	public ServerResponse<Product> getProduct(int productId) {
		Product product = productMapper.selectByPrimaryKey(productId);
		if (product == null) {
			return ServerResponse.createErrorResponse(ResponseCodeConst.ERROR_PRODUCT_ID);
		}
		return ServerResponse.createSuccessResponse(product);
	}

}
