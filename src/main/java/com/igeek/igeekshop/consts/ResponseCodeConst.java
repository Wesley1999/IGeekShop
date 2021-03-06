package com.igeek.igeekshop.consts;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author 王少刚
 * @Time 2019/3/7 11:43
 */

public class ResponseCodeConst {
	public static final Integer SUCCESS = 0;
	// 注册相关
	public static final Integer PASSWORD_NOT_SAME = 6001;
	public static final Integer ERROR_PASSWORD_LENGTH = 6002;
	public static final Integer ERROR_GENDER = 6003;
	public static final Integer ERROR_EMAIL = 6004;
	public static final Integer EMAIL_HAS_BEEN_REGISTERED = 6005;
	public static final Integer USERNAME_HAS_BEEN_REGISTERED = 6006;
	public static final Integer ERROR_TELEPHONE = 6007;
	public static final Integer NO_LOGIN_USER = 6008;
	public static final Integer ERROR_VERIFICATION_CODE = 6009;
//	public static final Integer SEND_EMAIL_FAILED = 6010;
	// 激活相关
	public static final Integer ERROR_ACTIVE_CODE = 6101;
	public static final Integer USER_HAS_ACTIVATED = 6102;
	// 登录相关
	public static final Integer ERROR_USERNAME_OR_PASSWORD = 6201;
	public static final Integer USER_HAS_NOT_ACTIVATED = 6202;
	// 其他
	public static  final Integer ERROR_PRODUCT_ID = 6301;
	public static  final Integer ERROR_USER_ID = 6302;
	public static  final Integer NEED_SIGN_IN = 6303;
	public static  final Integer CART_IS_EMPTY = 6304;
	public static  final Integer PERMISSION_DENIED = 6305;
	// 后台管理相关
	public static  final Integer CATEGORY_NAME_EXISTS = 6401;
	public static  final Integer ERROR_CATEGORY_ID = 6402;
	public static  final Integer CATEGORY_IS_USED_BY_PRODUCT = 6403;
	public static  final Integer PRODUCT_USED_BY_CART = 6404;
	public static  final Integer PRODUCT_USED_BY_ORDER = 6405;
	public static  final Integer AUTO_SIGN_IN_FAILED = 6406;
	public static  final Integer IMG_IS_TOO_SMALL = 6407;


	public static Map<Integer, String> responseMessage = new HashMap<Integer, String>() {{
		put(PASSWORD_NOT_SAME, "两次输入的密码不同");
		put(ERROR_PASSWORD_LENGTH, "密码长度有误");
		put(ERROR_GENDER, "性别有误");
		put(ERROR_EMAIL, "邮箱有误");
		put(EMAIL_HAS_BEEN_REGISTERED, "邮箱已被注册");
		put(USERNAME_HAS_BEEN_REGISTERED, "用户名已被注册");
		put(ERROR_TELEPHONE, "手机号有误");
		put(NO_LOGIN_USER, "无登录用户");
		put(ERROR_VERIFICATION_CODE, "验证码有误");
//		put(SEND_EMAIL_FAILED, "激活邮件发送失败，请检查邮箱是否正确，或稍后再试");
		put(ERROR_ACTIVE_CODE, "激活码有误");
		put(USER_HAS_ACTIVATED, "用户已激活");
		put(ERROR_USERNAME_OR_PASSWORD, "用户名或密码有误");
		put(USER_HAS_NOT_ACTIVATED, "用户未激活");
		put(ERROR_PRODUCT_ID, "productId有误");
		put(ERROR_USER_ID, "userId有误");
		put(NEED_SIGN_IN, "请先登录");
		put(CART_IS_EMPTY, "购物车为空");
		put(PERMISSION_DENIED, "没有权限");
		put(CATEGORY_NAME_EXISTS, "分类名称已存在");
		put(ERROR_CATEGORY_ID, "categoryId有误");
		put(CATEGORY_IS_USED_BY_PRODUCT, "分类有商品使用");
		put(PRODUCT_USED_BY_CART, "用户购物车中有此商品，不能删除");
		put(PRODUCT_USED_BY_ORDER, "订单明细中有此商品，不能删除");
		put(AUTO_SIGN_IN_FAILED, "自动登录失败");
		put(IMG_IS_TOO_SMALL, "图片过小，请上传高清图片");
	}};


	public static String getResponseMessageByResponseCode(Integer responseCode) {
		return responseMessage.get(responseCode);
	}
}
