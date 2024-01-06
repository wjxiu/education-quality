package com.github.wjxiu.conf;

import com.github.wjxiu.common.Exception.ClientException;
import com.github.wjxiu.common.token.UserContext;
import com.github.wjxiu.common.token.UserInfoDTO;
import com.github.wjxiu.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * @author xiu
 * @create 2024-01-06 20:47
 */
@Component
@Slf4j
@Order(0)
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String servletPath = request.getServletPath();
//        token过滤白名单
        List<String> list = Arrays.asList("/login","/register", "/setfortest", "/error","/createToken","/test");
        boolean inwhitelist = list.stream().anyMatch(servletPath::startsWith);
        if (inwhitelist)return true;
        String token = request.getHeader("Authorization");
        if (!StringUtils.hasLength(token))throw new  ClientException("缺少token");
        UserInfoDTO userInfoDTO = JWTUtil.parseJwtToken(token);
        if (userInfoDTO==null||!StringUtils.hasLength(userInfoDTO.getUserId()))throw new ClientException("token无效");
        UserContext.set(userInfoDTO);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}