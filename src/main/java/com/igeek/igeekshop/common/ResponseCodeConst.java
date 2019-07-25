package com.igeek.igeekshop.common;

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
//	public static final Integer SEND_EMAIL_FAILED = 6009;
	// 激活相关
	public static final Integer ERROR_ACTIVE_CODE = 6101;
	public static final Integer USER_HAS_ACTIVATED = 6102;
	// 登录相关
	public static final Integer ERROR_USERNAME_OR_PASSWORD = 6201;
	public static final Integer USER_HAS_NOT_ACTIVATED = 6202;
	// 其他
	public static  final Integer ERROR_PRODUCT_ID = 6301;
	public static  final Integer ERROR_USER_ID = 6302;


	public static Map<Integer, String> responseMessage = new HashMap<Integer, String>() {{
		put(PASSWORD_NOT_SAME, "两次输入的密码不同");
		put(ERROR_PASSWORD_LENGTH, "密码长度有误");
		put(ERROR_GENDER, "性别有误");
		put(ERROR_EMAIL, "邮箱有误");
		put(EMAIL_HAS_BEEN_REGISTERED, "邮箱已被注册");
		put(USERNAME_HAS_BEEN_REGISTERED, "用户名已被注册");
		put(ERROR_TELEPHONE, "手机号有误");
		put(NO_LOGIN_USER, "无登录用户");
//		put(SEND_EMAIL_FAILED, "激活邮件发送失败，请检查邮箱是否正确，或稍后再试");
		put(ERROR_ACTIVE_CODE, "激活码有误");
		put(USER_HAS_ACTIVATED, "用户已激活");
		put(ERROR_USERNAME_OR_PASSWORD, "用户名或密码有误");
		put(USER_HAS_NOT_ACTIVATED, "用户未激活");
		put(ERROR_PRODUCT_ID, "productId有误");
		put(ERROR_USER_ID, "userId有误");
	}};


	public static String getResponseMessageByResponseCode(Integer responseCode) {
		return responseMessage.get(responseCode);
	}
}
