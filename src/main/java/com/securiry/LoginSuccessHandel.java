package com.securiry;

import com.alibaba.fastjson.JSON;
import com.service.ConsumerService;
import com.util.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * 登录成功处理器
 */
@Component
public class LoginSuccessHandel implements AuthenticationSuccessHandler {

    @Resource
    private ConsumerService consumerService;

    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        String username = httpServletRequest.getParameter("username");

        consumerService.changeEndLoginTime(username, new Date());

        httpServletResponse.setCharacterEncoding("UTF-8");

        httpServletResponse.setContentType("application/json");

        PrintWriter writer = httpServletResponse.getWriter();

        Result result = new Result();

        result.success(200, "登录成功");

        writer.print(JSON.toJSON(result));

        writer.flush();
    }
}
