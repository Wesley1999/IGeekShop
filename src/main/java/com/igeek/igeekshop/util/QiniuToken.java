package com.igeek.igeekshop.util;

import com.qiniu.util.Auth;

/**
 * @Author 王少刚
 * @Time 2019/7/26 9:20
 */

public class QiniuToken {
	public static String getUpToken() {
		String accessKey = "XHCA4mTkIqLGWE6p7EavnTuPFIH2czGBm4mionsr";
		String secretKey = "n17X9B3USS0GLMEzVhfQfcwo_OPkNSXF0Ij0I-fs";
		String bucket = "test2";
		Auth auth = Auth.create(accessKey, secretKey);
		String upToken = auth.uploadToken(bucket);
		return upToken;
	}
}
