package com.igeek.igeekshop.service;

import com.igeek.igeekshop.common.OrderStatusConst;
import com.igeek.igeekshop.common.ResponseCodeConst;
import com.igeek.igeekshop.common.ServerResponse;
import com.igeek.igeekshop.mapper.*;
import com.igeek.igeekshop.pojo.*;
import com.igeek.igeekshop.util.UUIDUtils;
import com.igeek.igeekshop.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author 王少刚
 * @Time 2019/7/25 14:52
 */

@Service
public class OrderService {

	@Autowired
	OrdersMapper ordersMapper;

	@Autowired
	UserMapper userMapper;

	@Autowired
	CartItemMapper cartItemMapper;

	@Autowired
	ProductMapper productMapper;

	@Autowired
	OrderItemMapper orderItemMapper;

	public ServerResponse<String> submitOrder(String userId, String recipientName, String telephone, String address) {
		// 校验userId
		if (userMapper.selectByPrimaryKey(userId) == null) {
			return ServerResponse.createErrorResponse(ResponseCodeConst.ERROR_USER_ID);
		}

		// 获取购物车
		CartItemExample cartItemExample = new CartItemExample();
		cartItemExample.createCriteria().andUserIdEqualTo(userId);
		List<CartItem> cartItems = cartItemMapper.selectByExample(cartItemExample);

		// 购物车为空不能提交订单
		if (cartItems.isEmpty()) {
			return ServerResponse.createErrorResponse(ResponseCodeConst.CART_IS_EMPTY);
		}

		// 生成订单号
		String orderId = UUIDUtils.getUUID32();

		// 计算总金额
		double totalPrice = 0d;
		for (CartItem cartItem : cartItems) {
			totalPrice += cartItem.getCount() * productMapper.selectByPrimaryKey(cartItem.getProductId()).getShopPrice();
		}

		// 封装订单对象
		Orders orders = new Orders();
		orders.setOrderId(orderId);
		orders.setOrderTime(new Date());
		orders.setTotalPrice(totalPrice);
		orders.setRecipientName(recipientName);
		orders.setTelephone(telephone);
		orders.setAddress(address);
		orders.setStatus(OrderStatusConst.UNPAID);
		orders.setUserId(userId);

		// 插入到数据库
		ordersMapper.insert(orders);

		// 封装订单明细
		List<OrderItem> orderItemList = new ArrayList<>();
		for (CartItem cartItem : cartItems) {
			OrderItem orderItem = new OrderItem();
			orderItem.setProductId(cartItem.getProductId());
			orderItem.setCount(cartItem.getCount());
			orderItem.setOrderId(orderId);
			orderItemList.add(orderItem);
		}
		for (OrderItem orderItem : orderItemList) {
			// 插入到数据库
			orderItemMapper.insert(orderItem);
		}

		// 清空购物车
		cartItemMapper.deleteByExample(cartItemExample);

		return ServerResponse.createSuccessResponse();

	}

	public ServerResponse<List<OrderVo>> getOrders(String userId) {
		OrdersExample orderExample = new OrdersExample();
		orderExample.createCriteria().andUserIdEqualTo(userId);
		List<Orders> orders = ordersMapper.selectByExample(orderExample);
		List<OrderVo> orderVos = new ArrayList<>();

		for (Orders order : orders) {
			OrderItemExample orderItemExample = new OrderItemExample();
			orderItemExample.createCriteria().andOrderIdGreaterThanOrEqualTo(order.getOrderId());
			List<OrderItem> orderItemList = orderItemMapper.selectByExample(orderItemExample);

			OrderVo orderVo = new OrderVo();
			orderVo.setOrders(order);
			orderVo.setOrderItems(orderItemList);

			orderVos.add(orderVo);
		}
		return ServerResponse.createSuccessResponse(orderVos);
	}

}
