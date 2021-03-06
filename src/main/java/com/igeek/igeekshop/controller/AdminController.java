package com.igeek.igeekshop.controller;

import com.github.pagehelper.PageInfo;
import com.igeek.igeekshop.consts.DefaultValueConst;
import com.igeek.igeekshop.consts.ResponseCodeConst;
import com.igeek.igeekshop.consts.SessionKeyConst;
import com.igeek.igeekshop.pojo.Category;
import com.igeek.igeekshop.service.AdminService;
import com.igeek.igeekshop.util.ServerResponse;
import com.igeek.igeekshop.vo.OrderVo;
import com.igeek.igeekshop.vo.ProductVo;
import com.qiniu.common.QiniuException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
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
	@RequestMapping("get_categories.action")
	public ServerResponse<List> getCategories() {
		return adminService.getCategories();
	}

	// 添加分类
	@RequestMapping("add_category.action")
	public ServerResponse<String> addCategory(@RequestParam String name, @RequestParam String description) {
		return adminService.addCategory(name, description);
	}

	// 获取根据id分类详情
	@RequestMapping("get_category.action")
	public ServerResponse<Category> getCategory(@RequestParam int categoryId) {
		return adminService.getCategory(categoryId);
	}

	// 编辑分类
	@RequestMapping("update_category.action")
	public ServerResponse<String> updateCategory(@RequestParam int categoryId, @RequestParam String name, @RequestParam String description) {
		return adminService.updateCategory(categoryId, name, description);
	}

	// 删除分类
	@RequestMapping("delete_category.action")
	public ServerResponse<String> deleteCategory(@RequestParam int categoryId) {
		return adminService.deleteCategory(categoryId);
	}

	//--------------------------------------------------------------------------------------

	// 获取所有商品
	@RequestMapping("get_products.action")
	public ServerResponse<PageInfo> getProducts(@RequestParam(defaultValue = "") String keyword,
	                                            @RequestParam(defaultValue = DefaultValueConst.DEFAULT_PAGE_NUM) int pageNum,
	                                            @RequestParam(defaultValue = DefaultValueConst.DEFAULT_PAGE_SIZE) int pageSize,
	                                            @RequestParam(defaultValue = DefaultValueConst.DEFAULT_NAVIGATE_PAGES) int navigatePages) {
		return adminService.getProducts(keyword, pageNum, pageSize, navigatePages);
	}

	// 添加商品（含文件上传）
	// 仅post
	@RequestMapping(value = "add_product.action", method = RequestMethod.POST, consumes = "multipart/form-data")
	public ServerResponse<String> addProduct(@RequestParam String name, @RequestParam double marketPrice,
	                                         @RequestParam double shopPrice, @RequestParam String description,
	                                         @RequestParam boolean isNew, @RequestParam boolean isHot,
	                                         @RequestParam int categoryId, @RequestParam MultipartFile imageFile) throws IOException {
		try {
			return adminService.addProduct(name, marketPrice, shopPrice, description, isNew, isHot, categoryId, imageFile);
		}catch (Exception e) {
			return ServerResponse.createErrorResponse(ResponseCodeConst.IMG_IS_TOO_SMALL);
		}
	}

	// 根据id获取商品详情
	@RequestMapping("get_product.action")
	public ServerResponse<ProductVo> getProduct(int productId) {
		return adminService.getProduct(productId);
	}

	// 编辑商品
	@RequestMapping("update_product.action")
	public ServerResponse<String> updateProduct(@RequestParam int productId, @RequestParam String name, @RequestParam double marketPrice,
	                                            @RequestParam double shopPrice, @RequestParam String description,
	                                            @RequestParam boolean isNew, @RequestParam boolean isHot,
	                                            @RequestParam int categoryId) {
		return adminService.updateProduct(productId, name, marketPrice, shopPrice, description, isNew, isHot, categoryId);
	}

	// 修改商品图片
	@RequestMapping("update_product_image.action")
	public ServerResponse<String> updateProductImage(@RequestParam int productId, @RequestParam MultipartFile imageFile) throws QiniuException {
		try {
			return adminService.updateProductImage(productId, imageFile);
		} catch (Exception e) {
			return ServerResponse.createErrorResponse(ResponseCodeConst.IMG_IS_TOO_SMALL);
		}
	}

	// 删除商品
	@RequestMapping("delete_product.action")
	public ServerResponse<String> deleteProduct(@RequestParam int productId) {
		return adminService.deleteProduct(productId);
	}

	// 获取所有订单
	@RequestMapping("get_orders.action")
	public ServerResponse<PageInfo<List<OrderVo>>> getOrders(@RequestParam(defaultValue = DefaultValueConst.DEFAULT_PAGE_NUM) int pageNum,
	                                               @RequestParam(defaultValue = DefaultValueConst.DEFAULT_PAGE_SIZE) int pageSize,
	                                               @RequestParam(defaultValue = DefaultValueConst.DEFAULT_NAVIGATE_PAGES) int navigatePages) {
		return adminService.getOrders(pageNum, pageSize, navigatePages);
	}

	// 退出登录
	@RequestMapping("sign_out_for_admin.action")
	public ServerResponse<String> signInForAdmin(HttpSession session) {
		session.removeAttribute(SessionKeyConst.IS_ADMIN);
		return ServerResponse.createSuccessResponse();
	}

}
