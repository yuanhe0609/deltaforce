package com.company.project.intercepter;

import com.company.project.entity.Result;
import com.company.project.entity.User;
import com.company.project.mapper.UserMapper;
import com.company.project.utils.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    UserMapper userMapper;

    private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String accessToken = request.getHeader("Access-Token");
        String refreshToken = request.getHeader("Refresh-Token");
        Map<String,String> tokenMap = new HashMap<>();
        tokenMap.put("Access-Token",accessToken);
        tokenMap.put("Refresh-Token",refreshToken);
        if (accessToken == null && refreshToken == null) {
            returnJson(response,new Result().error("Token为空").toJson());
            return false;
        }
        Result tokenVerifyIfo = JWTUtil.checkToken(tokenMap);
        if(tokenVerifyIfo.getCode() == 500){
            returnJson(response,new Result().error(tokenVerifyIfo.getMsg()).toJson());
            return false;
        }
        Map<String,String> data = (Map<String, String>) tokenVerifyIfo.getData();
        String username = data.get("username");
        User user = userMapper.selectUserByUsername(username);
        if (user == null) {
            returnJson(response,new Result().error("无对应用户").toJson());
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }

    private void returnJson(HttpServletResponse response,Object obj) throws Exception {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(obj);

        } catch (IOException e) {
        } finally {
            if (writer != null) {
                writer.close();
            }
        }

    }
}

