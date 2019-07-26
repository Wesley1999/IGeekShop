package com.igeek.igeekshop.util;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.storage.Configuration;

import java.io.File;

/**
 * @Author 王少刚
 * @Time 2019/7/26 9:43
 */

public class MyUploadUtils {
	public static String upload(File file) throws QiniuException {
		Configuration configuration = new Configuration(Zone.zone0());
		String upToken = QiniuToken.getUpToken();
		String fileName = UUIDUtils.getUUID32() + ".png";
		new UploadManager(configuration).put(file, fileName, upToken);
		return "http://pv4pbq6xn.bkt.clouddn.com/" + fileName;
	}

}
