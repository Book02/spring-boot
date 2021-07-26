package com.shu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {

    @RequestMapping({"/", "/index"})
    public String toIndex(ModelMap modelMap){
        modelMap.addAttribute("msg","hello,shiro");
        return  "index";
    }

    @RequestMapping("/add")
    public String add(){

        return  "user/add";
    }

    @RequestMapping("update")
    public String update(ModelMap modelMap){

        return  "user/update";
    }
}
