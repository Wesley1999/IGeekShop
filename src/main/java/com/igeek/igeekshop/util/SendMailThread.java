package com.igeek.igeekshop.util;

/**
 * @Author 王少刚
 * @Time 2019/7/25 10:47
 */

public class SendMailThread extends Thread {

	private String email;
	private String emailMsg;

	public SendMailThread(String email, String emailMsg) {
		this.email = email;
		this.emailMsg = emailMsg;
	}

	@Override
	public void run() {
		EmailUtils.sendEmail(email, emailMsg);
	}
}
