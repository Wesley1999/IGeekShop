package com.igeek.igeekshop.util;

import java.util.UUID;

/**
 * @Author 王少刚
 * @Time 2019/7/25 9:56
 */

// 参考https://blog.csdn.net/xinghuo0007/article/details/72868799
public class UUIDUtils {
	public static String getUUID32() {
		return UUID.randomUUID().toString().replace("-", "").toLowerCase();
	}
}
