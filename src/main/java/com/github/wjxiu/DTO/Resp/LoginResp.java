package com.github.wjxiu.DTO.Resp;

import lombok.Data;

/**
 * @author xiu
 * @create 2024-01-06 22:10
 */
@Data
public class LoginResp {
    Integer type;//0管理员,1教师，2学生
    Integer id;
    String name;
    String token;
}
