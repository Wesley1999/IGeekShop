import com.igeek.igeekshop.util.MailUtils;
import org.junit.Test;

/**
 * @Author 王少刚
 * @Time 2019/7/31 9:37
 */

public class EmailDemo {
	@Test
	public void test01() {
		String msg = "<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" dir=\"LTR\" style=\"\n" +
				"\t\tbackground-color: #F0F7FC;\n" +
				"\t\tborder: 1px solid #A5CAE4;\n" +
				"\t\tborder-radius: 5px;\n" +
				"\t\tdirection: LTR;\">\n" +
				"\t<tbody><tr>\n" +
				"\t\t<td style=\"\n" +
				"\t\t\tbackground-color: #D7EDFC;\n" +
				"\t\t\tpadding: 5px 10px;\n" +
				"\t\t\tborder-bottom: 1px solid #A5CAE4;\n" +
				"\t\t\tborder-top-left-radius: 4px;\n" +
				"\t\t\tborder-top-right-radius: 4px;\n" +
				"\t\t\tfont-family: 'Trebuchet MS', Helvetica, Arial, sans-serif;\n" +
				"\t\t\tfont-size: 11px;\n" +
				"\t\t\tline-height: 1.231;\">\n" +
				"\t\t\t<a href=\"https://www.applex.net/\" style=\"color: #176093; text-decoration:none\" rel=\"noopener\" target=\"_blank\">极限苹果</a>\n" +
				"\t\t</td>\n" +
				"\t</tr>\n" +
				"\t<tr>\n" +
				"\t\t<td style=\"\n" +
				"\t\t\tbackground-color: #FCFCFF;\n" +
				"\t\t\tpadding: 1em;\n" +
				"\t\t\tcolor: #141414;\n" +
				"\t\t\tfont-family: 'Trebuchet MS', Helvetica, Arial, sans-serif;\n" +
				"\t\t\tfont-size: 13px;\n" +
				"\t\t\tline-height: 1.231;\">\n" +
				"\t\t\t<p style=\"margin-top: 0\">adasfsafds，为了完成您在 <a href=\"https://www.applex.net/\" style=\"color: #176093; text-decoration: none\" rel=\"noopener\" target=\"_blank\">极限苹果</a> 的注册流程，或重新激活账户，请您点击下面的链接以确认您的邮件地址。</p>\n" +
				"\n" +
				"<h2 style=\"font-size: 14pt; font-weight: normal; margin: 10px 0 4px\"><a href=\"https://www.applex.net/account-confirmation/141994/email?c=f8HlI9s4dVVHs1Uf\" style=\"color: #176093; text-decoration: none\" rel=\"noopener\" target=\"_blank\">确认账户/Confirm Account</a></h2>\n" +
				"<div style=\"color: #176093; font-size: 11px; margin: 4px 0 10px\"><a href=\"https://www.applex.net/account-confirmation/141994/email?c=f8HlI9s4dVVHs1Uf\" style=\"color: #176093; text-decoration: none\" rel=\"noopener\" target=\"_blank\">https://www.applex.net/account-confirmation/141994/email?c=f8HlI9s4dVVHs1Uf</a></div>\n" +
				"\n" +
				"<p>感谢注册！<br>\n" +
				"极限苹果</p>\n" +
				"\t\t</td>\n" +
				"\t</tr>\n" +
				"\t<tr>\n" +
				"\t\t<td style=\"\n" +
				"\t\t\tbackground-color: #F0F7FC;\n" +
				"\t\t\tpadding: 5px 10px;\n" +
				"\t\t\tborder-top: 1px solid #D7EDFC;\n" +
				"\t\t\tborder-bottom-left-radius: 4px;\n" +
				"\t\t\tborder-bottom-right-radius: 4px;\n" +
				"\t\t\ttext-align: right;\n" +
				"\t\t\tfont-family: 'Trebuchet MS', Helvetica, Arial, sans-serif;\n" +
				"\t\t\tfont-size: 11px;\n" +
				"\t\t\tline-height: 1.231;\">\n" +
				"\t\t\t<a href=\"https://www.applex.net/\" style=\"color: #176093; text-decoration: none\" rel=\"noopener\" target=\"_blank\">https://www.applex.net/</a>\n" +
				"\t\t</td>\n" +
				"\t</tr>\n" +
				"\t</tbody></table>";
		MailUtils.sendMail("1095151731@qq.com", msg);
	}
}
