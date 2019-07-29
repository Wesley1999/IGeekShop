package com.igeek.igeekshop.service;

import com.igeek.igeekshop.consts.SessionKeyConst;
import com.igeek.igeekshop.consts.ResponseCodeConst;
import com.igeek.igeekshop.util.ServerResponse;
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

		Object cartObj = session.getAttribute(SessionKeyConst.CART_VO_LIST);
		if (cartObj == null) {
			// 如果session中还没有购物车，创建购物车
			List<CartVo> cartVoList = new ArrayList<>();
			session.setAttribute(SessionKeyConst.CART_VO_LIST, cartVoList);
		}
		// 至此，可以确保已有购物车
		List<CartVo> cartVoList = (List<CartVo>) session.getAttribute(SessionKeyConst.CART_VO_LIST);

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
			cartVo.setCount(1);
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
		session.setAttribute(SessionKeyConst.CART_VO_LIST, cartVoList);
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

	public ServerResponse<List<CartVo>> getCartForUnLoggedUser(HttpSession session) {
		Object cartObj = session.getAttribute(SessionKeyConst.CART_VO_LIST);
		if (cartObj == null) {
			// 如果session中还没有购物车，创建购物车
			List<CartVo> cartVoList = new ArrayList<>();
			session.setAttribute(SessionKeyConst.CART_VO_LIST, cartVoList);
		}
		// 至此，可以确保已有购物车
		List<CartVo> cartVoList = (List<CartVo>) session.getAttribute(SessionKeyConst.CART_VO_LIST);
		return ServerResponse.createSuccessResponse(cartVoList);
	}

	public ServerResponse<List<CartVo>> getCartForLoggedUser(String userId) {
		// 校验userId
		if (userMapper.selectByPrimaryKey(userId) == null) {
			return ServerResponse.createErrorResponse(ResponseCodeConst.ERROR_USER_ID);
		}

		CartItemExample cartItemExample = new CartItemExample();
		cartItemExample.createCriteria().andUserIdEqualTo(userId);
		List<CartItem> cartItems = cartItemMapper.selectByExample(cartItemExample);
		List<CartVo> cartVoList = new ArrayList<>();
		for (CartItem cartItem : cartItems) {
			CartVo cartVo = new CartVo();
			cartVo.setProductId(cartItem.getProductId());
			cartVo.setCount(cartItem.getCount());
			cartVoList.add(cartVo);
		}
		return ServerResponse.createSuccessResponse(cartVoList);
	}

	public ServerResponse<String> deleteFromCartUnLoggedUser(HttpSession session, int productId) {
		Object cartObj = session.getAttribute(SessionKeyConst.CART_VO_LIST);
		if (cartObj == null) {
			// 如果session中还没有购物车，创建购物车
			List<CartVo> cartVoList = new ArrayList<>();
			session.setAttribute(SessionKeyConst.CART_VO_LIST, cartVoList);
		}
		// 至此，可以确保已有购物车
		List<CartVo> cartVoList = (List<CartVo>) session.getAttribute(SessionKeyConst.CART_VO_LIST);

		for (CartVo cartVo : cartVoList) {
			if (cartVo.getProductId() == productId) {
				cartVoList.remove(cartVo);
				break;
			}
		}

		// 回写session
		session.setAttribute(SessionKeyConst.CART_VO_LIST, cartVoList);

		return ServerResponse.createSuccessResponse();
	}

	public ServerResponse<String> deleteFromCartLoggedUser(String userId, int productId) {
		// 校验userId
		if (userMapper.selectByPrimaryKey(userId) == null) {
			return ServerResponse.createErrorResponse(ResponseCodeConst.ERROR_USER_ID);
		}

		CartItemExample cartItemExample = new CartItemExample();
		cartItemExample.createCriteria().andUserIdEqualTo(userId).andProductIdEqualTo(productId);
		cartItemMapper.deleteByExample(cartItemExample);
		return ServerResponse.createSuccessResponse();
	}

	public ServerResponse<String> clearCartForUnLoggedUser(HttpSession session) {
		session.removeAttribute(SessionKeyConst.CART_VO_LIST);
		return ServerResponse.createSuccessResponse();
	}

	public ServerResponse<String> clearCartForLoggedUser(String userId) {
		// 校验userId
		if (userMapper.selectByPrimaryKey(userId) == null) {
			return ServerResponse.createErrorResponse(ResponseCodeConst.ERROR_USER_ID);
		}

		CartItemExample cartItemExample = new CartItemExample();
		cartItemExample.createCriteria().andUserIdEqualTo(userId);
		cartItemMapper.deleteByExample(cartItemExample);
		return ServerResponse.createSuccessResponse();
	}

	public ServerResponse<Double> getTotalPriceForUnLoggedUser(HttpSession session) {

		Object cartObj = session.getAttribute(SessionKeyConst.CART_VO_LIST);
		if (cartObj == null) {
			// 如果session中还没有购物车，创建购物车
			List<CartVo> cartVoList = new ArrayList<>();
			session.setAttribute(SessionKeyConst.CART_VO_LIST, cartVoList);
		}
		// 至此，可以确保已有购物车
		List<CartVo> cartVoList = (List<CartVo>) session.getAttribute(SessionKeyConst.CART_VO_LIST);
		double totalPrice = 0d;
		for (CartVo cartVo : cartVoList) {
			totalPrice += productMapper.selectByPrimaryKey(cartVo.getProductId()).getShopPrice() * cartVo.getCount();
		}
		return ServerResponse.createSuccessResponse(totalPrice);

	}

	public ServerResponse<Double> getTotalPriceLoggedUser(String userId) {
		// 校验userId
		if (userMapper.selectByPrimaryKey(userId) == null) {
			return ServerResponse.createErrorResponse(ResponseCodeConst.ERROR_USER_ID);
		}

		CartItemExample cartItemExample = new CartItemExample();
		cartItemExample.createCriteria().andUserIdEqualTo(userId);

		List<CartItem> cartItems = cartItemMapper.selectByExample(cartItemExample);
		double totalPrice = 0d;
		for (CartItem cartItem : cartItems) {
			totalPrice += productMapper.selectByPrimaryKey(cartItem.getProductId()).getShopPrice() * cartItem.getCount();
		}
		return ServerResponse.createSuccessResponse(totalPrice);
	}

}
