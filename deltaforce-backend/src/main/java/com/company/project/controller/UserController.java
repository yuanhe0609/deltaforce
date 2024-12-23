package com.company.project.controller;

import com.alibaba.fastjson2.JSONObject;
import com.company.project.entity.User;
import com.company.project.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    IUserService userService;

    @RequestMapping("/register")
    public String register(User user){
        return userService.register(user).toJsonString();
    }

    @RequestMapping("/login")
    public String login(@RequestBody User user){
        System.out.println(user);
        return userService.login(user).toJsonString();
    }
}
