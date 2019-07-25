package com.igeek.igeekshop.controller;

import com.igeek.igeekshop.common.CurrentUserInformationConst;
import com.igeek.igeekshop.common.ServerResponse;
import com.igeek.igeekshop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @Author 王少刚
 * @Time 2019/7/25 8:50
 */

@Controller
@ResponseBody
@RequestMapping("cart")
public class CartController {

	@Autowired
	CartService cartService;

	@RequestMapping("addToCart")
	public ServerResponse<String> addToCart(HttpSession session, int productId) {
		// 未登录的用户
		if (session.getAttribute(CurrentUserInformationConst.USER_ID) == null) {
			return cartService.addToCartForUnLoggedUser(session, productId);
		} else {
			// 已登录的用户
			String userId = (String) session.getAttribute(CurrentUserInformationConst.USER_ID);
			return cartService.addToCartForLoggedUser(userId, productId);
		}
	}

}
