package com.igeek.igeekshop.controller;

import cn.dsna.util.images.ValidateCode;
import com.igeek.igeekshop.consts.ResponseCodeConst;
import com.igeek.igeekshop.consts.SessionKeyConst;
import com.igeek.igeekshop.pojo.User;
import com.igeek.igeekshop.service.UserService;
import com.igeek.igeekshop.util.Md5Utils;
import com.igeek.igeekshop.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * @Author 王少刚
 * @Time 2019/7/25 8:46
 */

@Controller
@ResponseBody
@RequestMapping("api/user")
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping("get_verification_code")
	public void getVerificationCode(HttpSession session, HttpServletResponse response,
	                                @RequestParam(defaultValue = "100") int width, @RequestParam(defaultValue = "30") int height,
	                                @RequestParam(defaultValue = "4") int codeCount, @RequestParam(defaultValue = "6") int lineCount) throws IOException {
		ValidateCode validateCode = new ValidateCode(width, height, codeCount, lineCount);
		String code = validateCode.getCode().toLowerCase();
		System.out.println("验证码是：" + code);
		session.setAttribute(SessionKeyConst.VERIFICATION_CODE, code);
		validateCode.write(response.getOutputStream());
	}

	@RequestMapping("get_whether_username_exists")
	public ServerResponse<Boolean> getWhetherUsernameExists(@RequestParam String username) {
		return userService.getWhetherUsernameExists(username);
	}

	@RequestMapping("get_whether_email_exists")
	public ServerResponse<Boolean> getWhetherEmailExists(@RequestParam String email) {
		return userService.getWhetherEmailExists(email);
	}

	@RequestMapping("register")
	//	birthday格式为"yyyy-MM-dd"或"yyyy-MM-dd HH:mm:ss"
	public ServerResponse<String> register(HttpSession session, @RequestParam String username, @RequestParam String password,
	                                       @RequestParam String repeatPassword, @RequestParam String email,
	                                       @RequestParam String name, @RequestParam int gender,
	                                       @RequestParam Date birthday, @RequestParam String telephone,
	                                       @RequestParam String verificationCode) {
		return userService.register(session, username, password, repeatPassword, email, name, gender, birthday, telephone, verificationCode);
	}

	@RequestMapping("active")
	public ServerResponse<String> active(@RequestParam String activeCode) {
		return userService.active(activeCode);
	}

	@RequestMapping("sign_in")
	public ServerResponse<String> signIn(HttpSession session, @RequestParam String username, @RequestParam String password,
	                                     @RequestParam String verificationCode, @RequestParam boolean remember,
	                                     @RequestParam boolean autoSignIn, HttpServletResponse httpServletResponse) {
		ServerResponse<String> response = userService.signIn(session, username, password, verificationCode);
		if (response.whetherSuccess()) {
			// 写cookie
			Cookie rememberCookie;
			if (remember) {
				rememberCookie = new Cookie("remember", "true");
			} else {
				rememberCookie = new Cookie("remember", "false");
			}
			rememberCookie.setMaxAge(Integer.MAX_VALUE);
			rememberCookie.setPath("/");
			httpServletResponse.addCookie(rememberCookie);

			Cookie usernameCookie = new Cookie("username", username);
			Cookie md5Md5PasswordCookie = new Cookie("md5Md5Password", Md5Utils.getMd5(Md5Utils.getMd5(password)));
			if (autoSignIn) {
				usernameCookie.setMaxAge(60 * 60 * 24 * 7);
				md5Md5PasswordCookie.setMaxAge(60 * 60 * 24 * 7);
			} else {
				usernameCookie.setMaxAge(0);
				md5Md5PasswordCookie.setMaxAge(0);
			}

			usernameCookie.setPath("/");
			md5Md5PasswordCookie.setPath("/");
			httpServletResponse.addCookie(usernameCookie);
			httpServletResponse.addCookie(md5Md5PasswordCookie);
		}
		return response;
	}

	@RequestMapping("sign_in_for_admin")
	public ServerResponse<String> signInForAdmin(HttpSession session, @RequestParam String username, @RequestParam String password) {
		if (username.equals("admin") && password.equals("123456")) {
			session.setAttribute(SessionKeyConst.IS_ADMIN, "1");
			return ServerResponse.createSuccessResponse();
		} else {
			return ServerResponse.createErrorResponse(ResponseCodeConst.ERROR_USERNAME_OR_PASSWORD);
		}
	}

	@RequestMapping("get_current_user")
	public ServerResponse<User> getCurrentUser(HttpSession session) {
		Object userId = session.getAttribute(SessionKeyConst.USER_ID);
		if (userId == null) {
			return ServerResponse.createErrorResponse(ResponseCodeConst.NO_LOGIN_USER);
		}
		return userService.getCurrentUser((String) userId);
	}

	@RequestMapping("sign_out")
	public ServerResponse<String> signOut(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		session.removeAttribute(SessionKeyConst.USER_ID);
		session.removeAttribute(SessionKeyConst.USERNAME);
		session.removeAttribute(SessionKeyConst.CART_VO_LIST);

		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("md5Md5Password")) {
				cookie.setValue("");
				cookie.setMaxAge(0);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
		}
		return ServerResponse.createSuccessResponse();
	}

	@RequestMapping("auto_sign_in")
	public ServerResponse<String> autoSignIn(HttpServletRequest request, HttpSession session) {
		Cookie[] cookies = request.getCookies();
		String username = "";
		String md5Md5Password = "";
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("username")) {
				username = cookie.getValue();
			}
			if (cookie.getName().equals("md5Md5Password")) {
				md5Md5Password = cookie.getValue();
			}
		}
		return userService.autoSignIn(username, md5Md5Password, session);
	}

}
