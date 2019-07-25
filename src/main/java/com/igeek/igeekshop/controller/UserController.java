package com.igeek.igeekshop.controller;

import com.igeek.igeekshop.common.ServerResponse;
import com.igeek.igeekshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
//	birthday格式为
	public ServerResponse<String> register(@RequestParam String username, @RequestParam String password,
	                                       @RequestParam String repeatPassword, @RequestParam String email,
	                                       @RequestParam String name, @RequestParam int gender,
	                                       @RequestParam Date birthday, @RequestParam String telephone,
	                                       @RequestParam String verificationCode) {
		return userService.register(username, password, repeatPassword, email, name, gender, birthday, telephone, verificationCode);
	}

}
