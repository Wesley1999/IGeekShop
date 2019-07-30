package com.igeek.igeekshop.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.igeek.igeekshop.consts.ResponseCodeConst;
import com.igeek.igeekshop.consts.SessionKeyConst;
import com.igeek.igeekshop.mapper.CategoryMapper;
import com.igeek.igeekshop.util.ServerResponse;
import com.igeek.igeekshop.mapper.ProductMapper;
import com.igeek.igeekshop.pojo.Product;
import com.igeek.igeekshop.pojo.ProductExample;
import com.igeek.igeekshop.vo.BrowsingHistoryVo;
import com.igeek.igeekshop.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 王少刚
 * @Time 2019/7/25 14:28
 */

@Service
public class ProductService {

	@Autowired
	ProductMapper productMapper;

	@Autowired
	CategoryMapper categoryMapper;

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

	public ServerResponse<ProductVo> getProduct(int productId) {
		Product product = productMapper.selectByPrimaryKey(productId);
		if (product == null) {
			return ServerResponse.createErrorResponse(ResponseCodeConst.ERROR_PRODUCT_ID);
		}
		ProductVo productVo = new ProductVo();
		productVo.setProductId(product.getProductId());
		productVo.setName(product.getName());
		productVo.setMarketPrice(product.getMarketPrice());
		productVo.setShopPrice(product.getShopPrice());
		productVo.setImgUrl(product.getImgUrl());
		productVo.setDescription(product.getDescription());
		productVo.setIsNew(product.getIsNew());
		productVo.setIsHot(product.getIsHot());
		productVo.setCategoryId(product.getCategoryId());
		productVo.setCategoryName(categoryMapper.selectByPrimaryKey(product.getCategoryId()).getName());
		return ServerResponse.createSuccessResponse(productVo);
	}

	public ServerResponse<String> recordBrowsingHistory(HttpSession session, int productId) {

		Product product = productMapper.selectByPrimaryKey(productId);
		if (product == null) {
			return ServerResponse.createErrorResponse(ResponseCodeConst.ERROR_PRODUCT_ID);
		}

		List<BrowsingHistoryVo> browsingHistoryVoList = (List<BrowsingHistoryVo>) session.getAttribute(SessionKeyConst.BROWSING_HISTORT);
		if (browsingHistoryVoList == null) {
			browsingHistoryVoList = new ArrayList<>();
			session.setAttribute(SessionKeyConst.BROWSING_HISTORT, browsingHistoryVoList);
		}

		BrowsingHistoryVo browsingHistoryVo = new BrowsingHistoryVo();
		browsingHistoryVo.setProductId(productId);
		browsingHistoryVo.setImgUrl(product.getImgUrl());
		browsingHistoryVo.setProductName(product.getName());

		// 如果在浏览记录中
		for (int i = 0; i < browsingHistoryVoList.size(); i++) {
			if (browsingHistoryVoList.get(i).getProductId().equals(productId)) {
				browsingHistoryVoList.remove(i);
				browsingHistoryVoList.add(0, browsingHistoryVo);
				return ServerResponse.createSuccessResponse();
			}
		}
		if (browsingHistoryVoList.size() >= 8) {
			// 如果不在浏览记录中，且已有的浏览记录小于8条
			browsingHistoryVoList.remove(7);
		}
		// 如果不在浏览记录中，且已有的浏览记录大于等于8条
		browsingHistoryVoList.add(0, browsingHistoryVo);
		return ServerResponse.createSuccessResponse();

	}

	public ServerResponse<List> getBrowsingHistory(HttpSession session) {
		List<BrowsingHistoryVo> browsingHistoryVoList = (List<BrowsingHistoryVo>) session.getAttribute(SessionKeyConst.BROWSING_HISTORT);
		if (browsingHistoryVoList == null) {
			browsingHistoryVoList = new ArrayList<>();
			session.setAttribute(SessionKeyConst.BROWSING_HISTORT, browsingHistoryVoList);
		}

		return ServerResponse.createSuccessResponse(browsingHistoryVoList);
	}

}
