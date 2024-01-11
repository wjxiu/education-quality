package com.github.wjxiu.DTO.Req;

import lombok.Data;

/**
 * 提交评价的请求体
 * @author xiu
 * @create 2024-01-11 21:02
 */
@Data
public class EvalSubmitReq {
    Integer id;
    Integer stuId;
    Integer evalItemId;
    Integer teacherId;
    //班级id
    Integer rate;
    Integer stuClassId;
}
