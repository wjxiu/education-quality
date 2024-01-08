package com.github.wjxiu.DTO;

import lombok.Data;

/**
 * @author xiu
 * @create 2024-01-06 22:10
 */
@Data
public class LoginResp {
    int type;//0管理员,1教师，2学生
    int id;
    String name;
    String token;
}
