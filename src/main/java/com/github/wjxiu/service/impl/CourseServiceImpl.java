package com.github.wjxiu.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.wjxiu.DO.CourseDO;
import com.github.wjxiu.DO.StuClassDO;
import com.github.wjxiu.DO.StudentCourseClassTeacherDO;
import com.github.wjxiu.common.Exception.ClientException;
import com.github.wjxiu.mapper.CourseMapper;
import com.github.wjxiu.mapper.StuClassMapper;
import com.github.wjxiu.service.CourseService;
import com.github.wjxiu.service.StuClassService;
import com.github.wjxiu.service.StudentCourseClassTeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiu
 * @description 针对表【course(课程表)】的数据库操作Service实现
 * @createDate 2024-01-06 21:20:38
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, CourseDO>
        implements CourseService {
     CourseMapper courseMapper;
     StuClassService stuClassService;
     StudentCourseClassTeacherService studentCourseClassTeacherService;
     @Autowired
    public void setCourseMapper(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }
    @Autowired
    public void setStuClassService(StuClassService stuClassService) {
        this.stuClassService = stuClassService;
    }
    @Autowired
    public void setStudentCourseClassTeacherService(StudentCourseClassTeacherService studentCourseClassTeacherService) {
        this.studentCourseClassTeacherService = studentCourseClassTeacherService;
    }

    @Override
    public List<String> getAllName() {
        return list(new LambdaQueryWrapper<CourseDO>().select(CourseDO::getCourseName))
                .stream()
                .map(CourseDO::getCourseName)
                .distinct()
                .toList();
    }

    @Override
    public Boolean courseNameisExit(String name) {
        return  count(new LambdaQueryWrapper<CourseDO>().eq(CourseDO::getCourseName, name))>0;

    }

    @Override
    public List<CourseDO> listPage(CourseDO course, Integer pageNum, Integer pageSize) {
       return  courseMapper.listPage(course,pageNum,pageSize);
    }

    @Override
    public boolean updateById(CourseDO entity) {
        CourseDO old = getById(entity.getId());
//        修改stu_class student_course_class_teacher course
//        student_course_class_teacher
        List<StudentCourseClassTeacherDO> list = studentCourseClassTeacherService.list(new LambdaQueryWrapper<StudentCourseClassTeacherDO>().eq(StudentCourseClassTeacherDO::getCourseName, old.getCourseName()));
        for (StudentCourseClassTeacherDO studentCourseClassTeacherDO : list) {
            studentCourseClassTeacherDO.setCourseName(entity.getCourseName());
        }
        boolean b = studentCourseClassTeacherService.updateBatchById(list);
        if (!b)throw new ClientException("更新学生关联表失败");
//        stu_class
        List<StuClassDO> stuClassDOS = stuClassService.list(new LambdaQueryWrapper<StuClassDO>().eq(StuClassDO::getCourseName, old.getCourseName()));
        for (StuClassDO stuClassDO : stuClassDOS) {
            stuClassDO.setDepartmentName(entity.getDepartmentName());
            stuClassDO.setMajorName(entity.getMajorName());
            stuClassDO.setCourseName(entity.getCourseName());
        }
        boolean b1 = stuClassService.updateBatchById(stuClassDOS);
        if (!b1)throw new ClientException("更新班级表失败");
        int i1 = courseMapper.updateById(entity);
        if (i1<1)throw new ClientException("更新课程表失败");
        return true;
    }
}




