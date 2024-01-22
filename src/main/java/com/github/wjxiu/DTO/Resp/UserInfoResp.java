package com.github.wjxiu.DTO.Resp;

import lombok.Data;

/**
 * @author xiu
 * @create 2024-01-22 11:17
 */
@Data
public class UserInfoResp {
    Integer id;
    String realName;
    Integer type;
    Boolean isAdmin;
    Boolean isStudent;

}
