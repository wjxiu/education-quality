package com.github.wjxiu.common.token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiu
 * @create 2023-11-20 16:05
 */
@Builder@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserInfoDTO {
    /**
     * 用户 ID
     */
    private Integer userId;


    /**
     * 真实姓名
     */
    private String realName;

    private Integer type;
    /**
     * 用户 Token
     */
    private String token;
}
