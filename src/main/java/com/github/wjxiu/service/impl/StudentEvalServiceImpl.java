package com.github.wjxiu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.wjxiu.DO.StudentEvalDO;
import com.github.wjxiu.DTO.Resp.EvalRateResp;
import com.github.wjxiu.mapper.StudentEvalMapper;
import com.github.wjxiu.service.StudentEvalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author xiu
* @description 针对表【student_eval】的数据库操作Service实现
* @createDate 2024-01-11 21:56:30
*/
@Service@RequiredArgsConstructor
public class StudentEvalServiceImpl extends ServiceImpl<StudentEvalMapper, StudentEvalDO>
    implements StudentEvalService {
    final StudentEvalMapper studentEvalMapper;

    @Override
    public List<EvalRateResp> getStudentRatePage(EvalRateResp resp,Integer pageNum,Integer pageSize) {
       return  studentEvalMapper.getStudentRatePage(resp,pageNum,pageSize);
    }
}




