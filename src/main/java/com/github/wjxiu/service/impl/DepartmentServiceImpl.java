package com.github.wjxiu.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.wjxiu.DO.*;
import com.github.wjxiu.common.Exception.ClientException;
import com.github.wjxiu.mapper.DepartmentMapper;
import com.github.wjxiu.mapper.MajorMapper;
import com.github.wjxiu.service.CourseService;
import com.github.wjxiu.service.DepartmentService;
import com.github.wjxiu.service.StuClassService;
import com.github.wjxiu.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author xiu
* @description 针对表【department(学院表)】的数据库操作Service实现
* @createDate 2024-01-06 21:21:00
*/
@RequiredArgsConstructor
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, DepartmentDO>
    implements DepartmentService {
    final DepartmentMapper departmentMapper;
    final MajorMapper mapper;
    final StudentService studentService;
    final CourseService courseService;
    final StuClassService stuClassService;
    @Override
    public List<DepartmentDO> pagelist(Integer id, String name, Integer pageNum, Integer pageSize) {
       return  departmentMapper.pagelist(id,name,pageNum,pageSize);
    }
    @Transactional(rollbackFor = Throwable.class)
    @Override
    public boolean updateById(DepartmentDO entity) {
        //        更新对应的关联的学生表，班级表，课程表
        DepartmentDO old = getById(entity.getId());
        String oldDepartName = old.getName();
        String newDepartName = entity.getName();
        List<StudentDO> list = studentService.list(new LambdaQueryWrapper<StudentDO>().eq(StudentDO::getDepartmentName, oldDepartName));
        list.forEach(stu->stu.setMajorName(newDepartName));
        studentService.updateBatchById(list);
        List<StuClassDO> stuClassDOList = stuClassService.list(new LambdaQueryWrapper<StuClassDO>().eq(StuClassDO::getDepartmentName, oldDepartName));
        stuClassDOList.forEach(stuClassDO -> stuClassDO.setMajorName(newDepartName));
        boolean b1 = stuClassService.updateBatchById(stuClassDOList);
        List<CourseDO> courseDOList = courseService.list(new LambdaQueryWrapper<CourseDO>().eq(CourseDO::getDepartmentName, oldDepartName));
        courseDOList.forEach(courseDO -> courseDO.setMajorName(newDepartName));
        boolean b = courseService.updateBatchById(courseDOList);
        boolean b2 = super.updateById(entity);
        if (!(b&&b1&&b2))throw new ClientException("更新专业过程中出现异常");
        return true;

    }
}




