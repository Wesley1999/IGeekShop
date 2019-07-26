package com.igeek.igeekshop.controller;

import com.github.pagehelper.PageInfo;
import com.igeek.igeekshop.consts.DefaultValueConst;
import com.igeek.igeekshop.pojo.Category;
import com.igeek.igeekshop.service.AdminService;
import com.igeek.igeekshop.util.ServerResponse;
import com.igeek.igeekshop.util.UUIDUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
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

	//-----------------------------------------------------------------------------------

	// 获取所有商品
	@RequestMapping("get_products")
	public ServerResponse<PageInfo> getProducts(@RequestParam(defaultValue = DefaultValueConst.DEFAULT_PAGE_NUM) int pageNum,
	                                            @RequestParam(defaultValue = DefaultValueConst.DEFAULT_PAGE_SIZE) int pageSize,
	                                            @RequestParam(defaultValue = DefaultValueConst.DEFAULT_NAVIGATE_PAGES) int navigatePages) {
		return adminService.getProducts(pageNum, pageSize, navigatePages);
	}

	// 添加商品（涉及文件上传）
	@RequestMapping("/updateItem.action")
	public ModelAndView edit(MultipartFile pictureFile) throws IOException {

		// 名称（不含扩展名）
		String name = UUIDUtils.getUUID32();
		// 扩展名
		String extension = FilenameUtils.getExtension(pictureFile.getOriginalFilename());
		// 保存图片
		pictureFile.transferTo(new File("M:\\upload\\" + name + "." + extension));

		ModelAndView mav = new ModelAndView();
		mav.setViewName("success");
		return mav;
	}

	// 根据id获取商品详情

	// 编辑商品（涉及文件上传）

	// 删除商品

}
