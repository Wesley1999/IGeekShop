package com.igeek.igeekshop.service;

import com.igeek.igeekshop.common.GenderConst;
import com.igeek.igeekshop.common.ResponseCodeConst;
import com.igeek.igeekshop.common.ServerResponse;
import com.igeek.igeekshop.mapper.UserMapper;
import com.igeek.igeekshop.pojo.User;
import com.igeek.igeekshop.pojo.UserExample;
import com.igeek.igeekshop.util.Md5Utils;
import com.igeek.igeekshop.util.SendMailThread;
import com.igeek.igeekshop.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author 王少刚
 * @Time 2019/7/25 9:01
 */

@Service
public class UserService {

	@Autowired
	UserMapper userMapper;

	public ServerResponse<String> register(String username, String password,
	                                       String repeatPassword, String email,
	                                       String name, int gender,
	                                       Date birthday, String telephone,
	                                       String verificationCode) {
		// todo 校验验证码

		// 校验密码
		if (!password.equals(repeatPassword)) {
			return ServerResponse.createErrorResponse(ResponseCodeConst.PASSWORD_NOT_SAME);
		}
		if (password.length() < 6 || password.length() > 16) {
			return ServerResponse.createErrorResponse(ResponseCodeConst.ERROR_PASSWORD_LENGTH);
		}
		// 校验性别
		if (!GenderConst.allGenders.contains(gender)) {
			return ServerResponse.createErrorResponse(ResponseCodeConst.ERROR_GENDER);
		}
		// 校验邮箱格式
		String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(email);
		boolean isMatched = matcher.matches();
		if (!isMatched) {
			return ServerResponse.createErrorResponse(ResponseCodeConst.ERROR_EMAIL);
		}
		// 校验邮箱是否已经注册过
		UserExample userExample = new UserExample();
		userExample.createCriteria().andEmailEqualTo(email);
		if (!userMapper.selectByExample(userExample).isEmpty()) {
			return ServerResponse.createErrorResponse(ResponseCodeConst.EMAIL_HAS_BEEN_REGISTERED);
		}
		// 校验用户名
		userExample.createCriteria().andUsernameNotEqualTo(username);
		if (!userMapper.selectByExample(userExample).isEmpty()) {
			return ServerResponse.createErrorResponse(ResponseCodeConst.EMAIL_HAS_BEEN_REGISTERED);
		}

		// 校验手机号
		check = "^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$";
		regex = Pattern.compile(check);
		matcher = regex.matcher(telephone);
		isMatched = matcher.matches();
		if (!isMatched) {
			return ServerResponse.createErrorResponse(ResponseCodeConst.ERROR_TELEPHONE);
		}

		// 生成userId
		String userId = UUIDUtils.getUUID32();
		// 加密密码
		String md5Password = Md5Utils.getMd5(password);

		// 封装对象
		User user = new User();
		user.setUserId(userId);
		user.setUsername(username);
		user.setPassword(md5Password);
		user.setName(name);
		user.setEmail(email);
		user.setGender(gender);
		user.setBirthday(birthday);
		user.setTelephone(telephone);
		user.setActiveStatus(false);

		// 调用多线程发送邮件
		// todo 邮件内容待修改
		String emailMsg = "<a href='http://localhost:8080/activeServlet?activeCode=" + userId + "'><h1>点击此链接激活</h1></a>";
		new SendMailThread(email, emailMsg).start();

		// 插入数据库
		userMapper.insert(user);

		return ServerResponse.createSuccessResponse();
	}

	public ServerResponse<String> active(String activeCode) {
		String userId = activeCode;
		User user = userMapper.selectByPrimaryKey(userId);
		if (user == null) {
			return ServerResponse.createErrorResponse(ResponseCodeConst.ERROR_ACTIVE_CODE);
		}
		if (user.getActiveStatus()) {
			return ServerResponse.createErrorResponse(ResponseCodeConst.USER_HAS_ACTIVATED);
		} else {
			user.setActiveStatus(true);
			userMapper.updateByPrimaryKeySelective(user);
			return ServerResponse.createSuccessResponse("激活成功");
		}
	}

	public ServerResponse<String> signIn(String username, String password){
		UserExample userExample = new UserExample();
		userExample.createCriteria().andUsernameEqualTo(username);
		List<User> users = userMapper.selectByExample(userExample);
		if (users.isEmpty()) {
			return ServerResponse.createErrorResponse(ResponseCodeConst.ERROR_USERNAME_OR_PASSWORD);
		}
		User user = users.get(0);
		if (!user.getPassword().equals(Md5Utils.getMd5(password))) {
			return ServerResponse.createErrorResponse(ResponseCodeConst.ERROR_USERNAME_OR_PASSWORD);
		}
		if (!user.getActiveStatus()) {
			return ServerResponse.createErrorResponse(ResponseCodeConst.USER_HAS_NOT_ACTIVATED);
		}
		if (user.getPassword().equals(Md5Utils.getMd5(password))) {
			return ServerResponse.createSuccessResponse();
		} else {
			return ServerResponse.createErrorResponse(ResponseCodeConst.ERROR_USERNAME_OR_PASSWORD);
		}
	}

	public ServerResponse<User> getCurrentUser(String userId) {
		return ServerResponse.createSuccessResponse(userMapper.selectByPrimaryKey(userId));
	}

	public User getUserByUserId(String userId) {
		User user = userMapper.selectByPrimaryKey(userId);
		if (user == null) {
			throw new RuntimeException("userId有误");
		}
		return user;
	}

	public User getUserByUsername(String username) {
		UserExample userExample = new UserExample();
		userExample.createCriteria().andUsernameEqualTo(username);
		List<User> users = userMapper.selectByExample(userExample);
		if (users.isEmpty()) {
			throw new RuntimeException("userId有误");
		}
		return users.get(0);
	}
}
