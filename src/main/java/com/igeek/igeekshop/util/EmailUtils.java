package com.igeek.igeekshop.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.Security;
import java.util.Date;
import java.util.Properties;

/**
 * @Author 王少刚
 * @Time 2019/7/31 20:51
 */

public class EmailUtils {
	/**
	 * 使用加密的方式,利用465端口进行传输邮件,开启ssl
	 * @param to    为收件人邮箱
	 * @param message    发送的消息
	 */
	public static void sendEmail(String to, String message) {
		try {
			Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
			final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
			//设置邮件会话参数
			Properties props = new Properties();
			//邮箱的发送服务器地址
			props.setProperty("mail.smtp.host", "smtp.163.com");
			props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
			props.setProperty("mail.smtp.socketFactory.fallback", "false");
			//邮箱发送服务器端口,这里设置为465端口
			props.setProperty("mail.smtp.port", "465");
			props.setProperty("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.auth", "true");
			final String username = "IGeekShop@163.com";
			final String password = "igeek2019";
			//获取到邮箱会话,利用匿名内部类的方式,将发送者邮箱用户名和密码授权给jvm
			Session session = Session.getDefaultInstance(props, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});
			//通过会话,得到一个邮件,用于发送
			Message msg = new MimeMessage(session);
			//设置发件人
			msg.setFrom(new InternetAddress("IGeekShop@163.com"));
			//设置收件人,to为收件人,cc为抄送,bcc为密送
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
			msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(to, false));
			msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(to, false));
			msg.setSubject("IGeekShop用户激活");
			//设置邮件消息
//			msg.setText(message);
			msg.setContent(message,"text/html;charset=gbk");
			//设置发送的日期
			msg.setSentDate(new Date());

			//调用Transport的send方法去发送邮件
			Transport.send(msg);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}