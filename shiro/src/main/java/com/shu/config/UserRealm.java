package com.shu.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

public class UserRealm extends AuthorizingRealm {

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权");
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了认证");


        String username="root";
        String password="123456";
        UsernamePasswordToken userToken = new UsernamePasswordToken(username,password);


        if (!userToken.getUsername().equals(username)){
            return null; //抛出异常 UnknownAccountException
        }
        //密码认证，shiro做~
        return new SimpleAuthenticationInfo("",password,"");

    }
}
