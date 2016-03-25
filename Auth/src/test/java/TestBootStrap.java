import com.netteans.auth.DBManagerFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by tanshengyong on 16/3/25.
 */
public class TestBootStrap {
    @Test
    public void test() {
        //1、获取 SecurityManager 工厂,此处使用 Ini 配置文件初始化 SecurityManager
        Factory<SecurityManager> factory = new DBManagerFactory("dblocal.xml");
        //2、得到 SecurityManager 实例 并绑定给 SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3、得到 Subject 及创建用户名/密码身份验证 Token(即用户身份/凭证)
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("ahaha", "123");
        try { //4、登录,即身份验证
            subject.login(token);
        } catch (AuthenticationException e) { //5、身份验证失败
            throw e;
        }
        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录
        //6、退出
        subject.logout();
        Assert.assertEquals(false, subject.isAuthenticated()); //断言用户已经登录
    }
}
