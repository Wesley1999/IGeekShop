package com.igeek.igeekshop.util;

import org.springframework.util.DigestUtils;

/**
 * @Author 王少刚
 * @Time 2018/12/22 19:55
 */

public class Md5Utils {

	// 加入一个盐值，用于混淆
	private final static String salt = "kiu4h61d2gfv";

	public static String getMd5(String string) {
		String base = string + salt;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}
}

