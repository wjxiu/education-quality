package com.github.wjxiu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.wjxiu.DO.EvalDO;
import com.github.wjxiu.DTO.Req.EvalSubmitReq;
import com.github.wjxiu.DTO.Resp.EvalMessageResp;
import com.github.wjxiu.DTO.Resp.EvalRateResp;

import java.util.List;

/**
* @author xiu
* @description 针对表【eval(评分选项表)】的数据库操作Service
* @createDate 2024-01-10 13:59:00
*/
public interface EvalService extends IService<EvalDO> {

    EvalMessageResp getTeacherEvalByStuidAndTeacherId(Integer stuId, Integer stuClassId, Integer teacherId);

    Boolean submit(EvalSubmitReq list);

    List<EvalDO> list(String evalItemName, Integer id,Integer pageNum, Integer pageSize);


}
