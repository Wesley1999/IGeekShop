package com.igeek.igeekshop.controller;

import com.igeek.igeekshop.common.CurrentUserInformationConst;
import com.igeek.igeekshop.common.ServerResponse;
import com.igeek.igeekshop.service.CartService;
import com.igeek.igeekshop.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

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

	@RequestMapping("add_to_art")
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

	@RequestMapping("get_cart")
	public ServerResponse<List<CartVo>> getCart(HttpSession session) {
		// 未登录的用户
		if (session.getAttribute(CurrentUserInformationConst.USER_ID) == null) {
			return cartService.getCartForUnLoggedUser(session);
		} else {
			// 已登录的用户
			String userId = (String) session.getAttribute(CurrentUserInformationConst.USER_ID);
			return cartService.getCartForLoggedUser(userId);
		}
	}

	@RequestMapping("delete_from_cart")
	// 无此商品不报错
	public ServerResponse<String> deleteFromCart(HttpSession session, int productId) {
		// 未登录的用户
		if (session.getAttribute(CurrentUserInformationConst.USER_ID) == null) {
			return cartService.deleteFromCartUnLoggedUser(session, productId);
		} else {
			// 已登录的用户
			String userId = (String) session.getAttribute(CurrentUserInformationConst.USER_ID);
			return cartService.deleteFromCartLoggedUser(userId, productId);
		}
	}

	@RequestMapping("clear_cart")
	public ServerResponse<String> clearCart(HttpSession session) {
		// 未登录的用户
		if (session.getAttribute(CurrentUserInformationConst.USER_ID) == null) {
			return cartService.clearCartForUnLoggedUser(session);
		} else {
			// 已登录的用户
			String userId = (String) session.getAttribute(CurrentUserInformationConst.USER_ID);
			return cartService.clearCartForLoggedUser(userId);
		}
	}

}
