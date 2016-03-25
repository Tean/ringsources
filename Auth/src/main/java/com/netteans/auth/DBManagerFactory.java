package com.netteans.auth;

import org.apache.shiro.authc.*;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.util.Factory;
import org.apache.shiro.mgt.SecurityManager;

import java.util.Arrays;

/**
 * Created by tanshengyong on 16/3/25.
 */
public class DBManagerFactory implements Factory<SecurityManager> {
    private String dbConfPath;

    public DBManagerFactory(String confpath) {
        dbConfPath = confpath;
    }

    public SecurityManager getInstance() {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealms(Arrays.asList(
                new Realm[]{
                        new Realm() {
                            public String getName() {
                                return "realm1";
                            }

                            public boolean supports(AuthenticationToken authenticationToken) {
                                return authenticationToken instanceof UsernamePasswordToken;
                            }

                            public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
                                String username = (String) authenticationToken.getPrincipal(); //得到用户名
                                String password = new String((char[]) authenticationToken.getCredentials()); //得到密码
                                if (!"ahaha".equals(username)) {
                                    throw new UnknownAccountException(); //如果用户名错误
                                }
                                if (!"123".equals(password)) {
                                    throw new IncorrectCredentialsException(); //如果密码错误
                                }
                                //如果身份认证验证成功,返回一个 AuthenticationInfo 实现;
                                return new SimpleAuthenticationInfo(username, password, getName());
                            }
                        }
                }));
        return securityManager;
    }
}
