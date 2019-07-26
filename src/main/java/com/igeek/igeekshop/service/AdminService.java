package com.igeek.igeekshop.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.igeek.igeekshop.consts.ResponseCodeConst;
import com.igeek.igeekshop.mapper.*;
import com.igeek.igeekshop.pojo.*;
import com.igeek.igeekshop.util.MyUploadUtils;
import com.igeek.igeekshop.util.ServerResponse;
import com.igeek.igeekshop.vo.OrderVo;
import com.qiniu.common.QiniuException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.util.ArrayList;
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

	@Autowired
	CartItemMapper cartItemMapper;

	@Autowired
	OrderItemMapper orderItemMapper;

	@Autowired
	OrdersMapper ordersMapper;

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

	//--------------------------------------------------------------------------------------

	public ServerResponse<PageInfo> getProducts(int pageNum, int pageSize, int navigatePages) {
		PageHelper.startPage(pageNum, pageSize);
		List<Product> products = productMapper.selectByExample(null);
		PageInfo pageResult = new PageInfo(products, navigatePages);
		pageResult.setList(products);
		return ServerResponse.createSuccessResponse(pageResult);
	}

	public ServerResponse<String> addProduct(String name, double marketPrice, double shopPrice, String description,
	                                         boolean isNew, boolean isHot, int categoryId, MultipartFile imageFile) throws QiniuException {

		// 校验分类id
		if (categoryMapper.selectByPrimaryKey(categoryId) == null) {
			return ServerResponse.createErrorResponse(ResponseCodeConst.ERROR_CATEGORY_ID);
		}

		// 先上传文件
		// MultipartFile转File，参考https://blog.csdn.net/alan_liuyue/article/details/79203168
		CommonsMultipartFile commonsmultipartfile = (CommonsMultipartFile) imageFile;
		DiskFileItem diskFileItem = (DiskFileItem) commonsmultipartfile.getFileItem();
		File file = diskFileItem.getStoreLocation();
		String imgUrl = MyUploadUtils.upload(file);
		// 删除临时文件
		file.delete();

		Product product = new Product();
		product.setName(name);
		product.setMarketPrice(marketPrice);
		product.setShopPrice(shopPrice);
		product.setDescription(description);
		product.setIsNew(isNew);
		product.setIsHot(isHot);
		product.setCategoryId(categoryId);
		product.setImgUrl(imgUrl);

		productMapper.insert(product);

		return ServerResponse.createSuccessResponse();
	}

	public ServerResponse<Product> getProduct(int productId) {
		// 校验productId
		Product product = productMapper.selectByPrimaryKey(productId);
		if (product == null) {
			return ServerResponse.createErrorResponse(ResponseCodeConst.ERROR_PRODUCT_ID);
		}
		return ServerResponse.createSuccessResponse(product);
	}

	public ServerResponse<String> updateProduct(int productId, String name, double marketPrice, double shopPrice, String description,
	                                         boolean isNew, boolean isHot, int categoryId) {
		// 校验productId
		if (productMapper.selectByPrimaryKey(productId) == null) {
			return ServerResponse.createErrorResponse(ResponseCodeConst.ERROR_PRODUCT_ID);
		}

		// 校验分类id
		if (categoryMapper.selectByPrimaryKey(categoryId) == null) {
			return ServerResponse.createErrorResponse(ResponseCodeConst.ERROR_CATEGORY_ID);
		}

		Product product = new Product();
		product.setProductId(productId);
		product.setName(name);
		product.setMarketPrice(marketPrice);
		product.setShopPrice(shopPrice);
		product.setDescription(description);
		product.setIsNew(isNew);
		product.setIsHot(isHot);
		product.setCategoryId(categoryId);

		productMapper.updateByPrimaryKeySelective(product);

		return ServerResponse.createSuccessResponse();
	}

	public ServerResponse<String> updateProductImage(int productId, MultipartFile imageFile) throws QiniuException {
		// 校验productId
		if (productMapper.selectByPrimaryKey(productId) == null) {
			return ServerResponse.createErrorResponse(ResponseCodeConst.ERROR_PRODUCT_ID);
		}

		// 先上传文件
		// MultipartFile转File，参考https://blog.csdn.net/alan_liuyue/article/details/79203168
		CommonsMultipartFile commonsmultipartfile = (CommonsMultipartFile) imageFile;
		DiskFileItem diskFileItem = (DiskFileItem) commonsmultipartfile.getFileItem();
		File file = diskFileItem.getStoreLocation();
		String imgUrl = MyUploadUtils.upload(file);
		// 删除临时文件
		file.delete();

		Product product = new Product();
		product.setProductId(productId);
		product.setImgUrl(imgUrl);
		productMapper.updateByPrimaryKeySelective(product);
		return ServerResponse.createSuccessResponse();
	}


	public ServerResponse<PageInfo<List<OrderVo>>> getOrders(int pageNum, int pageSize, int navigatePages) {
		PageHelper.startPage(pageNum, pageSize);
		List<Orders> orders = ordersMapper.selectByExample(null);
		PageInfo pageResult = new PageInfo(orders, navigatePages);
		List<OrderVo> orderVos = new ArrayList<>();
		for (Orders order : orders) {
			OrderItemExample orderItemExample = new OrderItemExample();
			orderItemExample.createCriteria().andOrderIdGreaterThanOrEqualTo(order.getOrderId());
			List<OrderItem> orderItemList = orderItemMapper.selectByExample(orderItemExample);

			OrderVo orderVo = new OrderVo();
			orderVo.setOrders(order);
			orderVo.setOrderItems(orderItemList);

			orderVos.add(orderVo);
		}
		pageResult.setList(orderVos);
		return ServerResponse.createSuccessResponse(pageResult);
	}


	public ServerResponse<String> deleteProduct(int productId) {
		// 如果购物车或订单中有此商品，不能删除
		CartItemExample cartItemExample = new CartItemExample();
		cartItemExample.createCriteria().andProductIdEqualTo(productId);
		List<CartItem> cartItems = cartItemMapper.selectByExample(cartItemExample);
		if (!cartItems.isEmpty()) {
			return ServerResponse.createErrorResponse(ResponseCodeConst.PRODUCT_USED_BY_CART);
		}

		OrderItemExample orderItemExample = new OrderItemExample();
		orderItemExample.createCriteria().andProductIdEqualTo(productId);
		List<OrderItem> orderItemList = orderItemMapper.selectByExample(orderItemExample);
		if (!orderItemList.isEmpty()) {
			return ServerResponse.createErrorResponse(ResponseCodeConst.PRODUCT_USED_BY_ORDER);
		}


		productMapper.deleteByPrimaryKey(productId);
		return ServerResponse.createSuccessResponse();
	}

}
