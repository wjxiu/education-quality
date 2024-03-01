package com.github.wjxiu.DTO.Req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.wjxiu.DTO.EvalSubmitItem;
import lombok.Data;

import java.util.List;

/**
 * 提交评价的请求体
 * @author xiu
 * @create 2024-01-11 21:02
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EvalSubmitReq {
    Integer studentId;
    //班级id
    Integer stuClassId;
    List<EvalSubmitItem> list;
    Integer teacherId;
//    学生对老师的评价
    String comment;
}
