package com.github.wjxiu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.wjxiu.DO.StuClassDO;
import com.github.wjxiu.DO.StudentCourseClassTeacherDO;
import com.github.wjxiu.mapper.EvalMapper;
import com.github.wjxiu.service.StudentCourseClassTeacherService;
import com.github.wjxiu.mapper.StudentCourseClassTeacherMapper;
import com.github.wjxiu.service.StudentEvalService;
import org.springframework.stereotype.Service;

/**
* @author xiu
* @description 针对表【student_course_class_teacher】的数据库操作Service实现
* @createDate 2024-01-11 21:50:31
*/
@Service
public class StudentCourseClassTeacherServiceImpl extends ServiceImpl<StudentCourseClassTeacherMapper, StudentCourseClassTeacherDO> implements StudentCourseClassTeacherService{

    /**
    * @author xiu
    * @description 针对表【stu_class(这个不是关系表，为了避开java关键字)】的数据库操作Service实现
    * @createDate 2024-01-14 00:08:53
    */
    @Service
    public static class StuClassServiceImpl extends ServiceImpl<EvalMapper.StuClassMapper, StuClassDO>
        implements StudentEvalService.StuClassService {

    }
}




