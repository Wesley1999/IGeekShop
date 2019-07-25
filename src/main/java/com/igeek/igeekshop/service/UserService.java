package com.igeek.igeekshop.service;

import com.igeek.igeekshop.consts.SessionKeyConst;
import com.igeek.igeekshop.consts.GenderConst;
import com.igeek.igeekshop.consts.ResponseCodeConst;
import com.igeek.igeekshop.util.ServerResponse;
import com.igeek.igeekshop.mapper.CartItemMapper;
import com.igeek.igeekshop.mapper.UserMapper;
import com.igeek.igeekshop.pojo.CartItem;
import com.igeek.igeekshop.pojo.CartItemExample;
import com.igeek.igeekshop.pojo.User;
import com.igeek.igeekshop.pojo.UserExample;
import com.igeek.igeekshop.util.Md5Utils;
import com.igeek.igeekshop.util.SendMailThread;
import com.igeek.igeekshop.util.UUIDUtils;
import com.igeek.igeekshop.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
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

	@Autowired
	CartItemMapper cartItemMapper;

	public ServerResponse<String> register(HttpSession session, String username, String password,
	                                       String repeatPassword, String email, String name, int gender,
	                                       Date birthday, String telephone, String verificationCode) {
		// 校验验证码（统一用小写）
		if (!verificationCode.toLowerCase().equals(session.getAttribute(SessionKeyConst.VERIFICATION_CODE))) {
			return ServerResponse.createErrorResponse(ResponseCodeConst.ERROR_VERIFICATION_CODE);
		}

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
		String emailMsg = "<a href='http://localhost:8080/user/active?activeCode=" + userId + "'><h1>点击此链接激活</h1></a>";
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

	public ServerResponse<String> signIn(HttpSession session, String username, String password) {
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

		session.setAttribute(SessionKeyConst.USER_ID, user.getUserId());
		session.setAttribute(SessionKeyConst.USERNAME, user.getUsername());

		List<CartVo> cartVoListInSession = (List<CartVo>) session.getAttribute(SessionKeyConst.CART_VO_LIST);

		CartItemExample cartItemExample = new CartItemExample();
		cartItemExample.createCriteria().andUserIdEqualTo(user.getUserId());
		List<CartItem> cartItems = cartItemMapper.selectByExample(cartItemExample);

		// 登录后把Session中的购物车数据持久化到数据库
		// 如果session中的购物车为空，直接结束
		if (cartVoListInSession == null) {
			return ServerResponse.createSuccessResponse();
		}
		// 数据库中存在的，修改数量
		for (CartItem cartItem : cartItems) {
			for (int i = 0; i < cartVoListInSession.size(); i++) {
				if (cartItem.getProductId() == cartVoListInSession.get(i).getProductId()) {
					cartItem.setCount(cartItem.getCount() + cartVoListInSession.get(i).getCount());
					cartVoListInSession.remove(i--);
				}
			}
		}
		for (CartItem cartItem : cartItems) {
			cartItemMapper.updateByPrimaryKeySelective(cartItem);
		}

		// 数据库中不存在的，添加到数据库
		cartItems.clear();
		for (CartVo cartVo : cartVoListInSession) {
			CartItem cartItem = new CartItem();
			cartItem.setProductId(cartVo.getProductId());
			cartItem.setCount(cartVo.getCount());
			cartItem.setUserId(user.getUserId());
			cartItems.add(cartItem);
		}
		for (CartItem cartItem : cartItems) {
			cartItemMapper.insert(cartItem);
		}

		// 移除session中的购物车信息

		return ServerResponse.createSuccessResponse();
	}

	public ServerResponse<User> getCurrentUser(String userId) {
		User user = userMapper.selectByPrimaryKey(userId);
		user.setPassword(null);
		user.setName(null);
		user.setEmail(null);
		user.setGender(null);
		user.setBirthday(null);
		user.setTelephone(null);
		user.setActiveStatus(null);
		return ServerResponse.createSuccessResponse(user);
	}

}
