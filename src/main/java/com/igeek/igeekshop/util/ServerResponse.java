package com.igeek.igeekshop.util;

import com.igeek.igeekshop.consts.ResponseCodeConst;
import lombok.Getter;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import java.io.Serializable;

/**
 * @Author 王少刚
 * @Time 2019/1/21 14:50
 */

@Getter
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse<T> implements Serializable {
	private int status;
	private String msg;
	// 泛型数据对象，为了通用，可以返回不同类型
	private T data;

	private ServerResponse(int status) {
		this.status = status;
	}

	private ServerResponse(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	private ServerResponse(int status, T data) {
		this.status = status;
		this.data = data;
	}

	public static <T> ServerResponse<T> createSuccessResponse() {
		return new ServerResponse<>(ResponseCodeConst.SUCCESS);
	}

	public static <T> ServerResponse<T> createSuccessResponse(T data) {
		return new ServerResponse<>(ResponseCodeConst.SUCCESS, data);
	}

	public static <T> ServerResponse<T> createErrorResponse(Integer responseCode) {
		return new ServerResponse<>(responseCode, ResponseCodeConst.getResponseMessageByResponseCode(responseCode));
	}

	public static <T> ServerResponse<T> createErrorResponse(Integer responseCode, String msg) {
		return new ServerResponse<>(responseCode, msg);
	}

	// 使之不在Json序列化结果当中
	// @JsonIgnore
	// @JsonIgnoreProperties
	// 这个方法名不要用isSuccess，否则会在序列化结果中多出一个名为success的boolean变量
	// 参考：https://blog.csdn.net/dushenzhi/article/details/52663181
	public boolean whetherSuccess() {
		return this.status == ResponseCodeConst.SUCCESS;
	}


}
