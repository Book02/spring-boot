package com.shu.config;

import com.shu.pojo.User;
import com.shu.service.UserService;
import com.shu.service.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //授权操作
        info.addStringPermission("user:add");

        //拿到当前登录的用户
//        Subject subject = SecurityUtils.getSubject();
//        User currentUser = (User) subject.getPrincipal(); //拿到user对象
//        System.out.println(currentUser.getPerms());
//        info.addStringPermission(currentUser.getPerms());

        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了认证");

      /*
        //用户名、密码  模拟从数据库中获取
        String name = "root";
        String password = "1111";
        if (!userToken.getUsername().equals(name)){
            return null; //抛出异常 UnknownAccountException
        }
        //密码认证，shiro做~
        return new SimpleAuthenticationInfo("",password,"");
        */

        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        //连接数据库

        User user = userService.queryUserByName(userToken.getUsername());
        if (user == null) {
            return null; //抛出异常 UnknownAccountException
        }
        //密码认证，shiro做~
        return new SimpleAuthenticationInfo(user, user.getPassword(), "");

    }
}

