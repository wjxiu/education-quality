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

import javax.servlet.http.Cookie;
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
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String servletPath = request.getServletPath();
//        token过滤白名单
        List<String> list = Arrays.asList("/user/login","/user/register","/user/logout","/favicon.ico", "/setfortest", "/error","/createToken","/test");
        boolean inwhitelist = list.stream().anyMatch(servletPath::startsWith);
        log.info("路径{}是否在白名单里：{}",servletPath,inwhitelist);
        if (inwhitelist)return true;
        String token = getToken(request);
        UserInfoDTO userInfoDTO = JWTUtil.parseJwtToken(token);
        if (userInfoDTO==null||!StringUtils.hasLength(userInfoDTO.getUserId().toString()))throw new ClientException("token无效");
        UserContext.set(userInfoDTO);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
    private String getToken(HttpServletRequest request){
        if (StringUtils.hasLength(request.getHeader("Authorization"))) {
            return  request.getHeader("Authorization");
        }
        if (StringUtils.hasLength(request.getQueryString())) {
            String temptoken = Arrays.stream(request.getQueryString().split("&")).map(param -> param.split("="))
                    .filter(keyValue -> keyValue.length == 2 && "token".equals(keyValue[0]))
                    .findFirst()
                    .map(keyValue -> keyValue[1])
                    .orElseThrow(() -> new ClientException("缺少token"));
            if (StringUtils.hasLength(temptoken)){
                return temptoken;
            }
        }
        if (request.getCookies()!=null&&request.getCookies().length>0){
            Cookie[] cookies = request.getCookies();
            String cookieName = "token"; // 替换成你要获取的Cookie的名称
            String cookieValue = Arrays.stream(cookies)
                    .filter(cookie -> cookie.getName().equals(cookieName))
                    .findFirst()
                    .map(Cookie::getValue)
                    .orElse(null);
            if (StringUtils.hasLength(cookieValue)){
                return cookieValue;
            }
        }
        throw new ClientException("没有token");
    }
}