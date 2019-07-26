package com.igeek.igeekshop.util;

import com.qiniu.common.QiniuException;
import com.qiniu.storage.Configuration;

import java.io.File;
import java.util.UUID;

/**
 * @Author 王少刚
 * @Time 2019/7/26 9:24
 */

public class Demo {
	public static void main(String[] args) throws QiniuException {
		File file = new File("C:\\Users\\Wesley\\Desktop\\新建文件夹\\c_0048.jpg");
		Configuration configuration = new Configuration();
		String upToken = QiniuToken.getUpToken();
		System.out.println(upToken);
		new UploadManager(configuration).put(file, UUID.randomUUID() + ".jpg", upToken);
	}
}
