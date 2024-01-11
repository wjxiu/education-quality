package com.github.wjxiu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.wjxiu.DO.EvalDO;
import com.github.wjxiu.DO.StudentEvalDO;
import com.github.wjxiu.DTO.Req.EvalSubmitReq;
import com.github.wjxiu.DTO.Resp.EvalRateReq;
import com.github.wjxiu.common.token.UserContext;
import com.github.wjxiu.mapper.StudentCourseClassTeacherMapper;
import com.github.wjxiu.service.EvalService;
import com.github.wjxiu.mapper.EvalMapper;
import com.github.wjxiu.service.StudentEvalService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiu
 * @description 针对表【eval(评分选项表)】的数据库操作Service实现
 * @createDate 2024-01-10 13:59:00
 */
@Service
@RequiredArgsConstructor
public class EvalServiceImpl extends ServiceImpl<EvalMapper, EvalDO> implements EvalService {
    final StudentCourseClassTeacherMapper studentCourseClassTeacherMapper;
    final StudentEvalService studentEvalService;
    final EvalMapper mapper;

    @Override
    public List<EvalRateReq> getTeacherEvalByStuidAndTeacherId(Integer stuId, Integer teacherId) {
        return mapper.getTeacherEvalByStuidAndTeacherId(stuId, teacherId);
    }

    @Override
    public Boolean submit(EvalSubmitReq submitReq) {
        StudentEvalDO studentEvalDO = new StudentEvalDO();
        BeanUtils.copyProperties(submitReq, studentEvalDO);
        studentEvalDO.setStuId(UserContext.getUserId());
        return studentEvalService.saveOrUpdate(studentEvalDO);
    }
}




