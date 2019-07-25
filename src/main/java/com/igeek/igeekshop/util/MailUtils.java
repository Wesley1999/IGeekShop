package com.igeek.igeekshop.util;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


/**
 *
 * @ClassName: MailUtils
 * @Description: 发送邮件辅助类
 *
 */
public class MailUtils {
	static final String smtphost = "smtp.163.com";// smtp服务器地址  这里是163  可以是其他的
	//	以下三个改成你自己的
	static final String from = "igeekshop@163.com"; // 邮件发送人的邮件地址
	static final String username = "igeekshop@163.com"; // 发件人的邮件帐户
	static final String password = "igeek2019"; // 发件人的邮件密码

	/**
	 * @param email 接收人的邮箱地址
	 * @param emailMsg 邮箱内容
	 * @return
	 */
	public static boolean sendMail(String email, String emailMsg) {

		// 定义Properties对象,设置环境信息
		Properties props = System.getProperties();

		// 设置邮件服务器的地址
		props.setProperty("mail.smtp.host", smtphost); // 指定的smtp服务器  简单的邮件协议
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.transport.protocol", "smtp");// 设置发送邮件使用的协议

		// 创建Session对象,不是网页里的session,这里的session对象表示整个邮件的环境信息
		Session session = Session.getInstance(props);
		// 设置输出调试信息
		session.setDebug(true);
		try {
			// Message的实例对象表示一封电子邮件
			MimeMessage message = new MimeMessage(session);
			// 设置发件人的地址
			message.setFrom(new InternetAddress(from));
			// 设置主题
			message.setSubject("IGeekShop用户激活");
			// 设置邮件的文本内容
			// message.setText("普通文本内容!");
			message.setContent((emailMsg), "text/html;charset=utf-8");

			// 设置附件
			// message.setDataHandler(dh);

			// 从session的环境中获取发送邮件的对象
			Transport transport = session.getTransport();
			// 连接邮件服务器 25是端口号
			transport.connect(smtphost, 25, username, password);
			// 设置收件人地址,并发送消息
			transport.sendMessage(message, new Address[] { new InternetAddress(email) });
			//关闭连接
			transport.close();
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
	}

}
