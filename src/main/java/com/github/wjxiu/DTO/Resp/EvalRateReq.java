package com.github.wjxiu.DTO.Resp;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author xiu
 * @create 2024-01-11 15:17
 */
@Data
public class EvalRateReq {
    Integer id;
    Integer stuId;
    Integer evalId;
    Integer rate;
    LocalDateTime updateTime;
    LocalDateTime createTime;
    String evalItem;
}
