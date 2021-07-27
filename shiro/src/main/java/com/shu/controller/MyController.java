package com.shu.controller;

import com.shu.service.UserService;
import com.shu.service.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

    @Autowired
    UserService userService;

    @RequestMapping({"/", "/index"})
    public String toIndex(ModelMap modelMap){
        modelMap.addAttribute("msg","hello,shiro");
        return  "index";
    }

    @RequestMapping("/add")
    public String add(){

        return  "user/add";
    }

    @RequestMapping("/update")
    public String update(ModelMap modelMap){
        return  "user/update";
    }

    @RequestMapping("/toLogin")
    public String toLogin(ModelMap modelMap){

        return  "login";
    }

    @RequestMapping("/login")
    public String login( String username, String password, Model model){
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try{
            subject.login(token); //执行登录的方法，如果没有异常就说明ok了
            return "index";
        }catch (UnknownAccountException e){ //用户名不存在
            model.addAttribute("msg","用户名不存在！");
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误！");
            return "login";
        }
    }

    @RequestMapping("/noAuth")
    @ResponseBody
    public String unauthorized(){
        return "未授权无法访问此页面";
    }


}
