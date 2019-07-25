package com.igeek.igeekshop.service;

import com.igeek.igeekshop.common.CurrentUserInformationConst;
import com.igeek.igeekshop.common.ResponseCodeConst;
import com.igeek.igeekshop.common.ServerResponse;
import com.igeek.igeekshop.mapper.CartItemMapper;
import com.igeek.igeekshop.mapper.ProductMapper;
import com.igeek.igeekshop.mapper.UserMapper;
import com.igeek.igeekshop.pojo.CartItem;
import com.igeek.igeekshop.pojo.CartItemExample;
import com.igeek.igeekshop.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 王少刚
 * @Time 2019/7/25 14:52
 */

@Service
public class CartService {

	@Autowired
	CartItemMapper cartItemMapper;

	@Autowired
	ProductMapper productMapper;

	@Autowired
	UserMapper userMapper;

	public ServerResponse<String> addToCartForUnLoggedUser(HttpSession session, int productId) {
		// 校验productId
		if (productMapper.selectByPrimaryKey(productId) == null) {
			return ServerResponse.createErrorResponse(ResponseCodeConst.ERROR_PRODUCT_ID);
		}

		Object cartObj = session.getAttribute(CurrentUserInformationConst.CART_VO_LIST);
		if (cartObj == null) {
			// 如果session中还没有购物车，创建购物车
			List<CartVo> cartVoList = new ArrayList<>();
			session.setAttribute(CurrentUserInformationConst.CART_VO_LIST, cartVoList);
		}
		// 至此，可以确保已有购物车
		List<CartVo> cartVoList = (List<CartVo>) session.getAttribute(CurrentUserInformationConst.CART_VO_LIST);

		// 用于判断购物车中是否有此商品
		boolean whetherProductInCart = false;
		for (CartVo cartVo : cartVoList) {
			if (cartVo.getProductId() == productId) {
				whetherProductInCart = true;
				break;
			}
		}
		if (!whetherProductInCart) {
			// 如果购物车中无此商品，添加
			CartVo cartVo = new CartVo();
			cartVo.setProductId(productId);
			cartVoList.add(cartVo);
		} else {
			// 如果购物车中有此商品，数量+1
			for (CartVo cartVo : cartVoList) {
				if (cartVo.getProductId() == productId) {
					cartVo.setCount(cartVo.getCount() + 1);
					break;
				}
			}
		}

		// 回写session
		session.setAttribute(CurrentUserInformationConst.CART_VO_LIST, cartVoList);
		return ServerResponse.createSuccessResponse();
	}

	public ServerResponse<String> addToCartForLoggedUser(String userId, int productId) {
		// 校验productId
		if (productMapper.selectByPrimaryKey(productId) == null) {
			return ServerResponse.createErrorResponse(ResponseCodeConst.ERROR_PRODUCT_ID);
		}

		// 校验userId
		if (userMapper.selectByPrimaryKey(userId) == null) {
			return ServerResponse.createErrorResponse(ResponseCodeConst.ERROR_USER_ID);
		}

		CartItemExample cartItemExample = new CartItemExample();
		cartItemExample.createCriteria().andUserIdEqualTo(userId).andProductIdEqualTo(productId);
		List<CartItem> cartItems = cartItemMapper.selectByExample(cartItemExample);
		if (cartItems.isEmpty()) {
			// 如果购物车中无此商品，添加
			CartItem cartItem = new CartItem();
			cartItem.setUserId(userId);
			cartItem.setProductId(productId);
			cartItem.setCount(1);
			cartItemMapper.insert(cartItem);
		} else {
			// 如果购物车中有此商品，数量+1
			CartItem cartItem = cartItems.get(0);
			cartItem.setCount(cartItem.getCount() + 1);
			cartItemMapper.updateByPrimaryKeySelective(cartItem);
		}
		return ServerResponse.createSuccessResponse();
	}

}
