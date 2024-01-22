package com.github.wjxiu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.wjxiu.DO.StudentCourseClassTeacherDO;

import java.util.List;

/**
* @author xiu
* @description 针对表【student_course_class_teacher】的数据库操作Mapper
* @createDate 2024-01-11 21:50:31
* @Entity generator.DO.StudentCourseClassTeacher
*/
public interface StudentCourseClassTeacherMapper extends BaseMapper<StudentCourseClassTeacherDO> {
  List<StudentCourseClassTeacherDO> getAllByTeacherId(Integer teacherId);
}




