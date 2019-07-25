package com.igeek.igeekshop.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author 王少刚
 * @Time 2019/3/7 11:43
 */

public class ResponseCodeConst {
	public static final Integer SUCCESS = 0;
	public static final Integer PASSWORD_NOT_SAME = 6001;
	public static final Integer ERROR_PASSWORD_LENGTH = 6002;
	public static final Integer ERROR_GENDER = 6003;
	public static final Integer ERROR_EMAIL = 6004;
	public static final Integer EMAIL_HAS_BEEN_REGISTERED = 6005;
	public static final Integer ERROR_TELEPHONE = 6006;
//	public static final Integer SEND_EMAIL_FAILED = 6007;


	public static Map<Integer, String> responseMessage = new HashMap<Integer, String>() {{
		put(PASSWORD_NOT_SAME, "两次输入的密码不同");
		put(ERROR_PASSWORD_LENGTH, "密码长度有误");
		put(ERROR_GENDER, "性别有误");
		put(ERROR_EMAIL, "邮箱有误");
		put(EMAIL_HAS_BEEN_REGISTERED, "邮箱已被注册");
		put(ERROR_TELEPHONE, "手机号有误");
//		put(SEND_EMAIL_FAILED, "激活邮件发送失败，请检查邮箱是否正确，或稍后再试");
	}};


	public static String getResponseMessageByResponseCode(Integer responseCode) {
		return responseMessage.get(responseCode);
	}
}
