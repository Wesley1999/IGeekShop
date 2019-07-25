package com.igeek.igeekshop.controller;

import com.igeek.igeekshop.common.CurrentUserInformationConst;
import com.igeek.igeekshop.common.ServerResponse;
import com.igeek.igeekshop.pojo.User;
import com.igeek.igeekshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @Author 王少刚
 * @Time 2019/7/25 8:46
 */

@Controller
@ResponseBody
@RequestMapping("user")
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping("register")
//	birthday格式为"yyyy-MM-dd"或"yyyy-MM-dd HH:mm:ss"
	public ServerResponse<String> register(@RequestParam String username, @RequestParam String password,
	                                       @RequestParam String repeatPassword, @RequestParam String email,
	                                       @RequestParam String name, @RequestParam int gender,
	                                       @RequestParam Date birthday, @RequestParam String telephone,
	                                       @RequestParam String verificationCode) {
		return userService.register(username, password, repeatPassword, email, name, gender, birthday, telephone, verificationCode);
	}


	@RequestMapping("active")
	public ServerResponse<String> active(@RequestParam String activeCode) {
		return userService.active(activeCode);
	}

	@RequestMapping("sign_in")
	public ServerResponse<String> signIn(HttpSession session, @RequestParam String userName, @RequestParam String password) {
		ServerResponse<String> response = userService.signIn(userName, password);
		if(response.whetherSuccess()) {
			User user = userService.getUserByUsername(userName);
			session.setAttribute(CurrentUserInformationConst.USER_ID, user.getUserId());
			session.setAttribute(CurrentUserInformationConst.USERNAME, user.getUsername());
		}
		return response;
	}

	@RequestMapping("get_current_user")
	public ServerResponse<User> getCurrentUser(HttpSession session) {
		return userService.getCurrentUser((String) session.getAttribute(CurrentUserInformationConst.USER_ID));
	}

	@RequestMapping("sign_out")
	public ServerResponse<String> signOut(HttpSession session) {
		session.removeAttribute(CurrentUserInformationConst.USER_ID);
		session.removeAttribute(CurrentUserInformationConst.USERNAME);
		return ServerResponse.createSuccessResponse();
	}

}