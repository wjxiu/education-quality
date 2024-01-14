package com.github.wjxiu.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.wjxiu.DO.StuClassDO;
import com.github.wjxiu.DO.StudentCourseClassTeacherDO;
import com.github.wjxiu.DO.TeacherDO;
import com.github.wjxiu.common.Exception.ClientException;
import com.github.wjxiu.mapper.StuClassMapper;
import com.github.wjxiu.service.CourseService;
import com.github.wjxiu.service.StuClassService;
import com.github.wjxiu.service.StudentCourseClassTeacherService;
import com.github.wjxiu.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
* @author xiu
* @description 针对表【stu_class】的数据库操作Service实现
* @createDate 2024-01-06 21:21:17
*/
@Service
@RequiredArgsConstructor
public class StuClassServiceImpl extends ServiceImpl<StuClassMapper, StuClassDO>
    implements StuClassService {
    final StuClassMapper  stuClassMapper;
    final TeacherService teacherService;
    final CourseService courseService;
    final StudentCourseClassTeacherService studentCourseClassTeacherService;
    @Override
    public List<StuClassDO> pageList(StuClassDO stuClassDO, Integer pageNum, Integer pageSize) {
       return  stuClassMapper.pageList(stuClassDO,pageNum,pageSize);
    }
    @Transactional(rollbackFor = Throwable.class)
    @Override
    public boolean updateById(StuClassDO entity) {
        Integer teacherId = entity.getTeacherId();
        TeacherDO teacherDO = teacherService.getById(teacherId);
        entity.setTeacherName(teacherDO.getRealName());
        List<StudentCourseClassTeacherDO> list = studentCourseClassTeacherService.list(new LambdaQueryWrapper<StudentCourseClassTeacherDO>().eq(StudentCourseClassTeacherDO::getStuClassId, entity.getId()));
        Boolean b = courseService.courseNameisExit(entity.getCourseName());
        for (StudentCourseClassTeacherDO studentCourseClassTeacherDO : list) {
            studentCourseClassTeacherDO.setStuClassName(entity.getName());
            studentCourseClassTeacherDO.setTeacherId(teacherDO.getId());
            studentCourseClassTeacherDO.setTeacherName(teacherDO.getRealName());
            studentCourseClassTeacherDO.setCourseId(entity.getCourseId());
        }
        if (!b)throw new ClientException("没有该课程");
       return  super.updateById(entity);
    }
}




