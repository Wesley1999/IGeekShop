package com.igeek.igeekshop.util;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author 王少刚
 * @Time 2019/7/25 9:34
 */

// 参考https://www.jianshu.com/p/28ebda42df71
public class DateConverter implements Converter<String, Date> {
	@Override
	// 用于将字符串格式时间转为Date类型
	public Date convert(String source) {
		String pattern = source.length() <= 10 ? "yyyy-MM-dd" : "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			return format.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
