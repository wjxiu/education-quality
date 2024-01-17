package com.github.wjxiu.service;

/**
 * @author xiu
 * @create 2024-01-11 21:58
 */

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.wjxiu.DO.StudentEvalDO;
import com.github.wjxiu.DTO.Resp.EvalRateResp;

import java.util.List;

/**
 * @author xiu
 * @description 针对表【student_eval】的数据库操作Service
 * @createDate 2024-01-11 21:56:30
 */
public interface StudentEvalService extends IService<StudentEvalDO> {
    List<EvalRateResp> getStudentRatePage(EvalRateResp resp,Integer pageNum,Integer pageSize);

}
