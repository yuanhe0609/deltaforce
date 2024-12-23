package com.company.project.service.impl;

import com.company.project.entity.Result;
import com.company.project.entity.User;
import com.company.project.mapper.UserMapper;
import com.company.project.service.IUserService;
import com.company.project.utils.JWTUtil;
import com.company.project.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    UserMapper userMapper;

    @Autowired
    MD5Util md5Util;

    @Override
    public Result register(User user) {
        Integer row = 0;
        if("".equals(user.getUsername()) || user.getUsername() == null){
            return new Result().error("用户名不能为空");
        }
        if(userMapper.selectUserByUsername(user.getUsername()) == null){
            String encryptPassword = md5Util.encryptMD5Salt(user.getPassword());
            user.setPassword(encryptPassword);
            row = userMapper.insertUser(user);
            if(row == 1){
                return new Result().success(row,"注册成功");
            }
        }
        return new Result().error("注册失败");
    }

    @Override
    public Result login(User user) {
        User nowUser = userMapper.selectUserByUsername(user.getUsername());
        if (nowUser != null) {
            if (md5Util.verify(user.getPassword(),nowUser.getPassword())) {
                String AccessToken = JWTUtil.createToken(user.getUsername(),3600000L);
                String RefreshToken = JWTUtil.createToken(user.getUsername(),2592000000L);
                Map<String,Object> map = new HashMap<>();
                map.put("AccessToken",AccessToken);
                map.put("RefreshToken",RefreshToken);
                map.put("uid",nowUser.getId());
                System.out.println(user);
                return new Result().success(map,"登录成功");
             }
        }
        return new Result().error("用户名或密码不正确");
    }
}
