package com.github.wjxiu.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.wjxiu.DO.*;
import com.github.wjxiu.DTO.Req.AddStuClassStudentReq;
import com.github.wjxiu.common.Exception.ClientException;
import com.github.wjxiu.mapper.StuClassMapper;
import com.github.wjxiu.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author xiu
* @description 针对表【stu_class】的数据库操作Service实现
* @createDate 2024-01-06 21:21:17
*/
@Service@Slf4j
@RequiredArgsConstructor
public class StuClassServiceImpl extends ServiceImpl<StuClassMapper, StuClassDO>
    implements StuClassService {
    final StuClassMapper  stuClassMapper;
    final TeacherService teacherService;
    final CourseService courseService;
    final StudentService studentService;
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
        if (teacherDO==null)throw new ClientException("没有该教师");
        entity.setTeacherName(teacherDO.getRealName());
        List<StudentCourseClassTeacherDO> list = studentCourseClassTeacherService.list(new LambdaQueryWrapper<StudentCourseClassTeacherDO>().eq(StudentCourseClassTeacherDO::getStuClassId, entity.getId()));
        Boolean b = courseService.courseNameisExit(entity.getCourseName());
        if (!b)throw new ClientException("没有该课程");
        for (StudentCourseClassTeacherDO studentCourseClassTeacherDO : list) {
            studentCourseClassTeacherDO.setTeacherId(teacherDO.getId());
            studentCourseClassTeacherDO.setTeacherName(teacherDO.getRealName());
            studentCourseClassTeacherDO.setCourseId(entity.getCourseId());
            studentCourseClassTeacherDO.setCourseName(entity.getCourseName());

            studentCourseClassTeacherDO.setStuClassName(entity.getName());
        }
       return  super.updateById(entity);
    }

    @Override
    public boolean save(StuClassDO entity){
        String courseName = entity.getCourseName();
        CourseDO one = courseService.getOne(new LambdaQueryWrapper<CourseDO>().eq(CourseDO::getCourseName, courseName));
        if (one==null)throw new ClientException("没有该课程");
        entity.setCourseId(one.getId());
        entity.setCourseName(one.getCourseName());
      return   stuClassMapper.insert(entity)>0;
    }

    @Override
    public boolean addStuClassStudent(AddStuClassStudentReq addStuClassStudentReq) {
        log.info("添加选课参数{}",addStuClassStudentReq);
        StuClassDO byId = getById(addStuClassStudentReq.getStuClassId());
        if (byId==null)throw new ClientException("没有这个班级");
        long count = studentCourseClassTeacherService.count(new LambdaQueryWrapper<StudentCourseClassTeacherDO>()
                .eq(StudentCourseClassTeacherDO::getStuClassId, addStuClassStudentReq.getStuClassId())
                .eq(StudentCourseClassTeacherDO::getTeacherId, addStuClassStudentReq.getStudentId())
                .eq(StudentCourseClassTeacherDO::getDelFlag,0));
        if (count>0)throw new ClientException("学生已经在班级里");
        StudentDO studentDO = studentService.getById(addStuClassStudentReq.getStudentId());
        if(studentDO==null)throw new ClientException("没有这名学生");
        StudentCourseClassTeacherDO studentCourseClassTeacherDO = new StudentCourseClassTeacherDO();
        BeanUtils.copyProperties(byId,studentCourseClassTeacherDO);
        studentCourseClassTeacherDO.setId(null);
        studentCourseClassTeacherDO.setCreateTime(null);
        studentCourseClassTeacherDO.setUpdateTime(null);
        studentCourseClassTeacherDO.setStuClassName(byId.getName());
        studentCourseClassTeacherDO.setStuClassId(byId.getId());
        studentCourseClassTeacherDO.setStudentId(addStuClassStudentReq.getStudentId());
        studentCourseClassTeacherDO.setStudentName(studentDO.getRealName());
        return studentCourseClassTeacherService.save(studentCourseClassTeacherDO);
    }

    @Override
    public List<StudentDO> getStuClassStudent(Integer stuClassId, Integer studentId, String studentName) {
        List<StudentCourseClassTeacherDO> list = studentCourseClassTeacherService
                .list(new LambdaQueryWrapper<StudentCourseClassTeacherDO>()
                        .eq(stuClassId!=null,StudentCourseClassTeacherDO::getStuClassId, stuClassId)
                        .eq(studentId!=null,StudentCourseClassTeacherDO::getStudentId,studentId)
                        .like(studentName!=null,StudentCourseClassTeacherDO::getStudentName,studentName));
        return list.stream().map(item -> {
            StudentDO studentDO = new StudentDO();
            studentDO.setRealName(item.getStudentName());
            studentDO.setId(item.getStudentId());
            return studentDO;
        }).toList();
    }

    @Override
    public boolean deleteClassStudent(Integer stuClassId, List<Integer> stuIds) {
       return  studentCourseClassTeacherService.remove(new LambdaQueryWrapper<StudentCourseClassTeacherDO>().eq(StudentCourseClassTeacherDO::getStuClassId,stuClassId)
                .in(StudentCourseClassTeacherDO::getStudentId,stuIds));
    }
}




