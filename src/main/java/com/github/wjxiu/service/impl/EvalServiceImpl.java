package com.github.wjxiu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.wjxiu.DO.EvalDO;
import com.github.wjxiu.DO.StudentEvalDO;
import com.github.wjxiu.DTO.EvalSubmitItem;
import com.github.wjxiu.DTO.Req.EvalSubmitReq;
import com.github.wjxiu.DTO.Resp.EvalRateResp;
import com.github.wjxiu.common.token.UserContext;
import com.github.wjxiu.mapper.StudentCourseClassTeacherMapper;
import com.github.wjxiu.service.EvalService;
import com.github.wjxiu.mapper.EvalMapper;
import com.github.wjxiu.service.StudentEvalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiu
 * @description 针对表【eval(评分选项表)】的数据库操作Service实现
 * @createDate 2024-01-10 13:59:00
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class EvalServiceImpl extends ServiceImpl<EvalMapper, EvalDO> implements EvalService {
    final StudentCourseClassTeacherMapper studentCourseClassTeacherMapper;
    final StudentEvalService studentEvalService;
    final EvalMapper mapper;

    @Override
    public List<EvalRateResp> getTeacherEvalByStuidAndTeacherId(Integer stuId, Integer teacherId) {
        return mapper.getTeacherEvalByStuidAndTeacherId(stuId, teacherId);
    }

    @Override
    public Boolean submit( EvalSubmitReq submitReq) {
        ArrayList<StudentEvalDO> list = new ArrayList<>();
        for (EvalSubmitItem item : submitReq.getList()) {
            StudentEvalDO studentEvalDO = new StudentEvalDO();
            studentEvalDO.setId(item.getId());
            studentEvalDO.setTeacherId(submitReq.getTeacherId());
            studentEvalDO.setStuClassId(submitReq.getStuClassId());
            studentEvalDO.setRate(item.getRate());
            studentEvalDO.setId(item.getId());
            studentEvalDO.setEvalId(item.getEvalItemId());
            studentEvalDO.setStuId(UserContext.getUserId());
            list.add(studentEvalDO);
        }
        log.info(String.valueOf(list));
        return studentEvalService.saveOrUpdateBatch(list);
    }

    @Override
    public List<EvalDO> list(String evalItemName,Integer id, Integer pageNum, Integer pageSize) {
      return   mapper.pagelist(evalItemName,id , pageNum, pageSize);
    }


}




