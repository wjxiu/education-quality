package com.github.wjxiu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.wjxiu.DO.StudentEvalDO;
import com.github.wjxiu.mapper.StudentMapper;
import com.github.wjxiu.service.StudentCourseClassTeacherService;
import com.github.wjxiu.service.StudentEvalService;
import org.springframework.stereotype.Service;

/**
* @author xiu
* @description 针对表【student_eval】的数据库操作Service实现
* @createDate 2024-01-11 21:56:30
*/
@Service
public class StudentEvalServiceImpl extends ServiceImpl<StudentMapper.StudentEvalMapper, StudentEvalDO>
    implements StudentEvalService {

}




