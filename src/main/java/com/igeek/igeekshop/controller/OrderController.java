package com.igeek.igeekshop.controller;

import com.igeek.igeekshop.common.CurrentUserInformationConst;
import com.igeek.igeekshop.common.ResponseCodeConst;
import com.igeek.igeekshop.common.ServerResponse;
import com.igeek.igeekshop.service.OrderService;
import com.igeek.igeekshop.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author 王少刚
 * @Time 2019/7/25 8:50
 */

@Controller
@ResponseBody
@RequestMapping("api/order")
public class OrderController {

	@Autowired
	OrderService orderService;

	@RequestMapping("submit_order")
	public ServerResponse<String> submitOrder(HttpSession session, @RequestParam String recipientName,
	                                          @RequestParam String telephone, @RequestParam String address) {
		// 必须已登录
		if (session.getAttribute(CurrentUserInformationConst.USER_ID) == null) {
			return ServerResponse.createErrorResponse(ResponseCodeConst.NEED_SIGN_IN);
		}

		String userId = (String) session.getAttribute(CurrentUserInformationConst.USER_ID);
		return orderService.submitOrder(userId, recipientName, telephone, address);
	}

	@RequestMapping("get_orders")
	public ServerResponse<List<OrderVo>> getOrders(HttpSession session) {
		// 必须已登录
		if (session.getAttribute(CurrentUserInformationConst.USER_ID) == null) {
			return ServerResponse.createErrorResponse(ResponseCodeConst.NEED_SIGN_IN);
		}

		String userId = (String) session.getAttribute(CurrentUserInformationConst.USER_ID);
		return orderService.getOrders(userId);
	}
}
