package com.igeek.igeekshop.interceptor;

import com.igeek.igeekshop.consts.ResponseCodeConst;
import com.igeek.igeekshop.consts.SessionKeyConst;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author 王少刚
 * @Time 2019/7/26 11:08
 */

public class IdentityInterceptor implements HandlerInterceptor {


	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws IOException {
		httpServletResponse.setHeader("Content-Type", "application/json;charset=UTF-8");
		HttpSession session = httpServletRequest.getSession();

		// 匹配url前缀
		String requestURI = httpServletRequest.getRequestURI();
		Pattern pattern = Pattern.compile("^/api/(.*)/.*$");
		Matcher matcher = pattern.matcher(requestURI);
		String prefix = "";
		if (matcher.find()) {
			// url中表示身份的前缀，如果是user，表示所有用户都可以访问
			prefix = matcher.group(1);
		}

		if (prefix.equals("order") && session.getAttribute(SessionKeyConst.USER_ID) == null) {
			httpServletResponse.getWriter().write("{\"status\":" + ResponseCodeConst.NEED_SIGN_IN
					+ ", \"msg\":\"" + ResponseCodeConst.getResponseMessageByResponseCode(ResponseCodeConst.NEED_SIGN_IN) + "\"}");
			return false;
		}

		if (prefix.equals("admin") && session.getAttribute(SessionKeyConst.IS_ADMIN) == null) {
			httpServletResponse.getWriter().write("{\"status\":" + ResponseCodeConst.PERMISSION_DENIED
					+ ", \"msg\":\"" + ResponseCodeConst.getResponseMessageByResponseCode(ResponseCodeConst.PERMISSION_DENIED) + "\"}");
			return false;
		}
		return true;
	}


	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object
			o, ModelAndView modelAndView) {

	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse
			httpServletResponse, Object o, Exception e) {

	}
}
