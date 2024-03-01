package com.github.wjxiu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.wjxiu.DO.EvalDO;
import com.github.wjxiu.DO.StudentCourseClassTeacherDO;
import com.github.wjxiu.DO.StudentEvalDO;
import com.github.wjxiu.DTO.EvalSubmitItem;
import com.github.wjxiu.DTO.Req.EvalSubmitReq;
import com.github.wjxiu.DTO.Resp.EvalMessageResp;
import com.github.wjxiu.DTO.Resp.EvalRateResp;
import com.github.wjxiu.common.Exception.ClientException;
import com.github.wjxiu.common.token.UserContext;
import com.github.wjxiu.mapper.StudentCourseClassTeacherMapper;
import com.github.wjxiu.service.EvalService;
import com.github.wjxiu.mapper.EvalMapper;
import com.github.wjxiu.service.StudentEvalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    public EvalMessageResp getTeacherEvalByStuidAndTeacherId(Integer stuId, Integer stuClassId, Integer teacherId) {
        List<EvalRateResp> teacherEvalByStuidAndTeacherId = mapper.getTeacherEvalByStuidAndTeacherId(stuId, teacherId);
        EvalMessageResp evalMessageResp = new EvalMessageResp();
        evalMessageResp.setList(teacherEvalByStuidAndTeacherId);
        StudentCourseClassTeacherDO studentCourseClassTeacherDO = studentCourseClassTeacherMapper.selectOne(new LambdaQueryWrapper<StudentCourseClassTeacherDO>()
                .eq(StudentCourseClassTeacherDO::getStudentId, stuId)
                .eq(StudentCourseClassTeacherDO::getStuClassId, stuClassId)
                .eq(StudentCourseClassTeacherDO::getTeacherId, teacherId).select(StudentCourseClassTeacherDO::getComment));
        evalMessageResp.setComment(studentCourseClassTeacherDO.getComment());
        return evalMessageResp;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
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
        boolean b = studentEvalService.saveOrUpdateBatch(list);
        StudentCourseClassTeacherDO relation = studentCourseClassTeacherMapper.selectOne(new LambdaQueryWrapper<StudentCourseClassTeacherDO>()
                .eq(StudentCourseClassTeacherDO::getTeacherId, submitReq.getTeacherId())
                .eq(StudentCourseClassTeacherDO::getStuClassId, submitReq.getStuClassId())
                .eq(StudentCourseClassTeacherDO::getStudentId, submitReq.getStudentId()));
        relation.setComment(submitReq.getComment());
        relation.setCommentTime(LocalDateTime.now());
        int insert = studentCourseClassTeacherMapper.updateById(relation);
        if (!b||insert<=0)throw new ClientException("评价不成功，请联系管理员");
        return true;
    }

    @Override
    public List<EvalDO> list(String evalItemName,Integer id, Integer pageNum, Integer pageSize) {
      return   mapper.pagelist(evalItemName,id , pageNum, pageSize);
    }
}




