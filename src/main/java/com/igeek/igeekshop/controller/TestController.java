package com.igeek.igeekshop.controller;

import cn.dsna.util.images.ValidateCode;
import com.igeek.igeekshop.consts.SessionKeyConst;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author 王少刚
 * @Time 2019/7/25 20:46
 */

@RequestMapping("test")
@Controller
public class TestController {

	@RequestMapping("get_verification_code")
	public void test01(HttpSession session, HttpServletResponse response) throws IOException {
		ValidateCode validateCode = new ValidateCode(100, 30, 4, 6);
		// 在校验时统一用小写
		String code = validateCode.getCode().toLowerCase();
		session.setAttribute(SessionKeyConst.VERIFICATION_CODE, code);
		validateCode.write(response.getOutputStream());
	}
}
