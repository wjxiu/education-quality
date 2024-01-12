package com.github.wjxiu.DTO.Req;

import com.github.wjxiu.DTO.EvalSubmitItem;
import lombok.Data;

import java.util.List;

/**
 * 提交评价的请求体
 * @author xiu
 * @create 2024-01-11 21:02
 */
@Data
public class EvalSubmitReq {
    Integer stuId;
    //班级id
    Integer stuClassId;
    List<EvalSubmitItem> list;
    Integer teacherId;
}
