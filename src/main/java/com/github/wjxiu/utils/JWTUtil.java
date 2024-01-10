

package com.github.wjxiu.utils;

import com.alibaba.fastjson2.JSON;
import com.github.wjxiu.common.token.UserInfoDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;



/**
 * JWT 工具类
 *
 */
@Slf4j
public final class JWTUtil {
    /**
     * 用户 ID Key
     */
    public static final String USER_ID_KEY = "userId";

    /**
     * 用户名 Key
     */
    public static final String TYPE ="type";

    /**
     * 用户真实名称 Key
     */
    public static final String REAL_NAME_KEY = "realName";

    /**
     * 用户 Token Key
     */
    public static final String USER_TOKEN_KEY = "token";

    private static final long EXPIRATION = 60 * 60 * 24 * 365L;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String ISS = "edu";
    public static final String SECRET = "SecretKey039245678901232039487623456783092349288901402967890140939827";
    /**
     * 生成用户 Token
     *
     * @param userInfo 用户信息
     * @return 用户访问 Token
     */
    public static String generateAccessToken(UserInfoDTO userInfo) {
        Map<String, Object> customerUserMap = new HashMap<>();
        customerUserMap.put(USER_ID_KEY, userInfo.getUserId());
        customerUserMap.put(TYPE, userInfo.getType());
        customerUserMap.put(REAL_NAME_KEY, userInfo.getRealName());
        String jwtToken = Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .setIssuedAt(new Date())
                .setIssuer(ISS)
                .setSubject(JSON.toJSONString(customerUserMap))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION * 1000))
                .compact();
        return TOKEN_PREFIX + jwtToken;
    }


    /**
     * 解析用户 Token
     *
     * @param jwtToken 用户访问 Token
     * @return 用户信息
     */
    public static UserInfoDTO parseJwtToken(String jwtToken) {
        if (StringUtils.hasText(jwtToken)) {
            String actualJwtToken = jwtToken.substring(TOKEN_PREFIX.length());
            try {
                Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(actualJwtToken).getBody();
                Date expiration = claims.getExpiration();
                if (expiration.after(new Date())) {
                    String subject = claims.getSubject();
                    System.out.println(subject);
                    return JSON.parseObject(subject, UserInfoDTO.class);
                }
            } catch (ExpiredJwtException ignored) {
            } catch (Exception ex) {
                log.error("JWT Token解析失败，请检查", ex);
            }
        }
        return null;
    }
}