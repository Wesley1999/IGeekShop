import com.igeek.igeekshop.util.EmailUtils;
import com.igeek.igeekshop.util.MailMsgUils;
import org.junit.Test;

/**
 * @Author 王少刚
 * @Time 2019/7/31 9:37
 */

public class EmailDemo {
	@Test
	public void test01() {
		EmailUtils.sendEmail("1095151731@qq.com", MailMsgUils.getEmailMsg("798465"));
	}
}
