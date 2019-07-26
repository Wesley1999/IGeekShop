package com.igeek.igeekshop.controller;

import cn.dsna.util.images.ValidateCode;
import com.igeek.igeekshop.consts.SessionKeyConst;
import com.igeek.igeekshop.consts.ResponseCodeConst;
import com.igeek.igeekshop.util.ServerResponse;
import com.igeek.igeekshop.pojo.User;
import com.igeek.igeekshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public void test01(HttpSession session, HttpServletResponse response,
	                   @RequestParam(defaultValue = "100") int width, @RequestParam(defaultValue = "30") int height,
	                   @RequestParam(defaultValue = "4") int codeCount, @RequestParam(defaultValue = "6") int lineCount) throws IOException {
		ValidateCode validateCode = new ValidateCode(width, height, codeCount, lineCount);
		String code = validateCode.getCode().toLowerCase();
		System.out.println("验证码是：" + code);
		session.setAttribute(SessionKeyConst.VERIFICATION_CODE, code);
		validateCode.write(response.getOutputStream());
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
	public ServerResponse<String> signIn(HttpSession session, @RequestParam String userName, @RequestParam String password) {
		return userService.signIn(session, userName, password);
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
	public ServerResponse<String> signOut(HttpSession session) {
		session.removeAttribute(SessionKeyConst.USER_ID);
		session.removeAttribute(SessionKeyConst.USERNAME);
		session.removeAttribute(SessionKeyConst.CART_VO_LIST);
		return ServerResponse.createSuccessResponse();
	}

}
