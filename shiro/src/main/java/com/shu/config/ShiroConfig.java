package com.shu.config;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //链接到前端:ShiroFilterFactoryBean 第三步
    @Bean
    public ShiroFilterFactoryBean getFilterFactoryBean(@Qualifier("securityManager") DefaultSecurityManager defaultSecurityManager){
        ShiroFilterFactoryBean bean= new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultSecurityManager);

        //添加shiro的内置过滤器
        /*
        anon: 无需认证即可访问
        authc: 必须认证才能用
        user: 必须拥有 “记住我” 功能才能用
        perms: 拥有对某个资源的权限才能用
        role: 拥有某个角色权限才能访问
        */
        Map<String,String> filterMap = new LinkedHashMap<>();
        //拦截
        filterMap.put("/add","anon");
        filterMap.put("/update","authc");
        bean. setFilterChainDefinitionMap(filterMap);


        //若访问时用户未认证，则跳转至登录页面
        bean.setLoginUrl("/toLogin");
        return bean;

    }

    //创建securityMassage对象 第二步
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager =new DefaultWebSecurityManager();
        //关联realm对象
        securityManager.setRealm(userRealm);
        return  securityManager;
    }

    //创建realm对象 第一步
    @Bean(name = "userRealm")
    public UserRealm userRealm(){
        return new UserRealm();
    }
}
