package com.igeek.igeekshop.util;

/**
 * @Author 王少刚
 * @Time 2019/7/31 15:55
 */

public class MailMsgUils {

	public static String getEmailMsg(String activeCode) {
		return "<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" dir=\"LTR\" style=\"\t\tbackground-color: #F0F7FC;\t\tborder: 1px solid #A5CAE4;\t\tborder-radius: 5px;\t\tdirection: LTR;\">\n" +
				"    <tbody>\n" +
				"        <tr>\n" +
				"            <td style=\"\t\t\tbackground-color: #D7EDFC;\t\t\tpadding: 5px 10px;\t\t\tborder-bottom: 1px solid #A5CAE4;\t\t\tborder-top-left-radius: 4px;\t\t\tborder-top-right-radius: 4px;\t\t\tfont-family: 'Trebuchet MS', Helvetica, Arial, sans-serif;\t\t\tfont-size: 13px;\t\t\tline-height: 1.231;\">\n" +
				"                <a href=\"http://www.igeekshop.com/\" style=\"color: #176093; text-decoration:none\" rel=\"noopener\" target=\"_blank\">\n" +
				"                    极客商城\n" +
				"                </a>\n" +
				"            </td>\n" +
				"        </tr>\n" +
				"        <tr>\n" +
				"            <td style=\"\t\t\tbackground-color: #FCFCFF;\t\t\tpadding: 1em;\t\t\tcolor: #141414;\t\t\tfont-family: 'Trebuchet MS', Helvetica, Arial, sans-serif;\t\t\tfont-size: 13px;\t\t\tline-height: 1.231;\">\n" +
				"                <p style=\"margin-top: 0\">尊敬的用户：</p><p style=\"margin-top: 0\">&nbsp; &nbsp; &nbsp; &nbsp;您好！您正在申请注册成为&nbsp;<a href=\"http://www.igeekshop.com/\" style=\"color: #176093; text-decoration: none\" rel=\"noopener\" target=\"_blank\">极客商城\n" +
				"                    </a>\n" +
				"                    的用户，请您点击下面的链接进行注册，或者</p><p style=\"margin-top: 0\">将此链接复制到浏览器中打开。</p>\n" +
				"                <h2 style=\"font-size: 10pt; font-weight: normal; margin: 10px 0 4px\">\n" +
				"                    <a href=\"http://localhost/activation?activeCode=" + activeCode + "\" style=\"color: #176093; text-decoration: none\" rel=\"noopener\" target=\"_blank\">\n" +
				"                       \n" +
				"                    </a>\n" +
				"                </h2>\n" +
				"                <div style=\"color: #176093; font-size: 11px; margin: 4px 0 10px\"><a href=\"http://localhost/activation.html?activeCode=" + activeCode + "\" style=\"color: #176093; text-decoration: none\" rel=\"noopener\" target=\"_blank\">http://localhost/activation.html?activeCode=" + activeCode + "</a></div>\n" +
				"                <p>(这是一封自动发送的邮件，请不要直接回复。）\n" +
				"                </p><p>\n" +
				"                    感谢您注册成为极客商城用户！</p><p><br></p><p>极客商城\n" +
				"                </p>\n" +
				"            </td>\n" +
				"        </tr>\n" +
				"        <tr>\n" +
				"            <td style=\"background-color: rgb(240, 247, 252); padding: 5px 10px; border-top: 1px solid rgb(215, 237, 252); border-bottom-left-radius: 4px; border-bottom-right-radius: 4px; text-align: right; line-height: 1.231; font-size: 11px; font-family: &quot;Trebuchet MS&quot;, Helvetica, Arial, sans-serif;\">\n" +
				"                <a href=\"http://www.igeekshop.com/\" style=\"color: #176093; text-decoration: none\" rel=\"noopener\" target=\"_blank\">\n" +
				"                    http://www.igeekshop.com/\n" +
				"                </a>\n" +
				"            </td>\n" +
				"        </tr>\n" +
				"    </tbody>\n" +
				"</table>\n";
	}

}
